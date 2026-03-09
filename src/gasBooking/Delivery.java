package gasBooking;

import java.util.*;
import java.util.concurrent.TimeUnit;
import Customers.*;

public class Delivery extends Booking {

    public String delPersonName;
    public Date deliveryDate; // previously dt_2
    public String status;

    // Constructor
    public Delivery(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile, numberOfCylinders);
        this.status = "P"; // Pending by default
    }

    // Input delivery person details
    public void delPersonDetails() {
        System.out.println("\nEnter delivery person name: ");
        delPersonName = new Scanner(System.in).nextLine();
    }

    // Amount and refund calculation
    public void amountCalc() {
        if (deliveryDate == null || bookingDate == null) {
            System.out.println("Booking or delivery date not set. Cannot calculate refund.");
            return;
        }
        long dayDiff = deliveryDate.getTime() - bookingDate.getTime();
        long days = TimeUnit.DAYS.convert(dayDiff, TimeUnit.MILLISECONDS);

        if (days > 7) {
            refund = 41.25;
            amount -= refund;
        }
    }

    // Override getDates to store deliveryDate
    @Override
    public void getDates() {
        super.getDates();
        this.deliveryDate = super.deliveryDate; // ensures Delivery class has access
    }

    // OTP verification
    @Override
    public void verifyOtp() {
        if ("B".equals(status)) {  // safe null check
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
            System.out.println("No valid booking found!");
        }
    }

    // Getter for number of cylinders
    @Override
    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }
}