package gasBooking;
import Customers.*;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Booking extends GasConnection {

    public double otp = 5678, amount = 980, refund = 0;

    public String dtBooking, dtDelivery, status, delMobileNo = "8080546085";
    public Date bookingDate;   // dt_1
    public Date deliveryDate;  // dt_2

    // Constructor
    public Booking(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile, numberOfCylinders);
        this.status = "P";  // default Pending
    }

    // Input booking and delivery dates
    public void getDates() {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.println("Enter booking date (dd/MM/yyyy):");
            dtBooking = sc.nextLine();
            bookingDate = dateFormat.parse(dtBooking);

            System.out.println("Enter delivery date (dd/MM/yyyy):");
            dtDelivery = sc.nextLine();
            deliveryDate = dateFormat.parse(dtDelivery);

            // Check difference between booking and delivery
            long difference = deliveryDate.getTime() - bookingDate.getTime();
            long diffDays = TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);

            if (diffDays > 7) {
                status = "P";  // Pending
            }

        } catch (Exception e) {
            System.out.println("Error in getDates: " + e);
        }
    }

    // Validate booking based on last booking date
    public void validate() {
        if (bookingDate == null) {
            System.out.println("Booking date is not set.");
            return;
        }

        if (lastDate == null) {
            lastDate = bookingDate; // first booking
        }

        long elapsedMs = bookingDate.getTime() - lastDate.getTime();
        long diffDays = TimeUnit.DAYS.convert(elapsedMs, TimeUnit.MILLISECONDS);
        System.out.println("Difference between last booking and current booking: " + diffDays + " days");

        if (numberOfCylinders == 1) {
            if (diffDays < 30) {
                System.out.println("Booking cannot be done (1 cylinder)");
                status = "C"; // Cancel
            } else {
                status = "B"; // Booked
                lastDate = bookingDate;
            }
        } else if (numberOfCylinders == 2) {
            if (diffDays < 50) {
                System.out.println("Booking cannot be done (2 cylinders)");
                status = "C"; // Cancel
            } else {
                status = "B"; // Booked
                lastDate = bookingDate;
            }
        }
    }

    // Getter for number of cylinders
    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    // OTP verification
    public void verifyOtp() {
        if ("B".equals(status)) {   // safe check for null
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter OTP:");
            int customerOtp = sc.nextInt();

            if (customerOtp != otp) {
                status = "C"; // Cancel
                System.out.println("OTP mismatch. Booking cancelled.");
            } else {
                status = "D"; // Delivered
                System.out.println("OTP verified. Delivery successful.");
            }
        } else {
            System.out.println("No booking found or booking is not valid!");
        }
    }

    // Refund/Amount calculation
    public void calculateRefund() {
        if (deliveryDate == null || bookingDate == null) {
            System.out.println("Booking or delivery date not set.");
            return;
        }

        long dayDiff = deliveryDate.getTime() - bookingDate.getTime();
        long days = TimeUnit.DAYS.convert(dayDiff, TimeUnit.MILLISECONDS);

        if (days > 7) {
            refund = 41.25;
            amount -= refund;
            System.out.println("Refund applied: " + refund + ", Net amount: " + amount);
        }
    }
}