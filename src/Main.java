import Customers.*;
import gasBooking.*;
import gasSupplier.*;

import java.text.SimpleDateFormat;
import java.util.*;

import static gasSupplier.gasAgency.*;

public class Main {

    static int count;
    static int bcount = 0, ccount = 0, dcount = 0, pcount = 0;
    static String dpname;

    // Cylinder count per month
    public static void cylinderCount(Delivery[] obj) {
        String[] month = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};

        for (Delivery delivery : obj) {
            count = 0;
            if (delivery.deliveryDate == null) continue; // safe check

            System.out.println("In month of " + month[delivery.deliveryDate.getMonth()] + " : ");
            System.out.println(" * In " + delivery.area);
            if ("D".equals(delivery.status)) {
                count += delivery.getNumberOfCylinders();
            }
            System.out.println(" - " + count + " cylinders delivered ");
        }
        System.out.println("\n");
    }

    // Late deliveries (> 7 days refund applied)
    public static void checkLateDel(Delivery[] obj) {
        String[] months = new String[]{"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        int[] monthCount = new int[12];

        for (Delivery delivery : obj) {
            if ("D".equals(delivery.status) && delivery.refund > 0) {
                monthCount[delivery.deliveryDate.getMonth()] += 1;
            }
        }

        System.out.println("------------------ Late Delivery -------------");
        for (int i = 0; i < 12; i++) {
            if (monthCount[i] != 0) {
                System.out.println("* In " + months[i] + " there are " + monthCount[i] + " late deliveries");
            }
        }
        System.out.println("\n");
    }

    // Single cylinder holders
    public static void numOfSingleCylinders(Delivery[] obj) {
        System.out.println("----------- Single Cylinder Holders ----------");
        for (int i = 0; i < obj.length; i++) {
            if (obj[i].getNumberOfCylinders() == 1) {
                System.out.println("* Customer Name: " + obj[i].name);
                System.out.println("* Mobile No: " + obj[i].mobile);
                System.out.println("* Gas Connection : " + (i + 101));
            }
        }
        System.out.println("\n");
    }

    // Delivery details by person
    public static void DeliveryDetails(Delivery[] obj) {
        System.out.println("------- Delivery Details ---------");
        System.out.println("Enter the name of delivery person:");
        dpname = new Scanner(System.in).nextLine();
        for (Delivery delivery : obj) {
            if ("D".equals(delivery.status) && dpname.equals(delivery.delPersonName)) {
                System.out.println("* Customer Name: " + delivery.name);
                System.out.println("- " + delivery.Street + ", " + delivery.area + ", " + delivery.pincode);
            }
        }
        System.out.println("\n");
    }

    // Delivery report
    public static void printReport(Delivery[] obj) {
        bcount = ccount = dcount = pcount = 0;
        System.out.println("-------- Delivery Report --------");
        for (Delivery delivery : obj) {
            if ("D".equals(delivery.status)) dcount++;
            else if ("B".equals(delivery.status)) bcount++;
            else if ("C".equals(delivery.status)) ccount++;
            else if ("P".equals(delivery.status)) pcount++;
        }
        System.out.println("* Booked: " + bcount);
        System.out.println("* Delivered: " + dcount);
        System.out.println("* Cancelled: " + ccount);
        System.out.println("* Pending: " + pcount);
        System.out.println("\n");
    }

    // Invoice
    public static void printInvoice(Delivery[] obj) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String invoiceDate = sdf.format(d);

        for (int i = 0; i < obj.length; i++) {
            Delivery delivery = obj[i];
            if ("D".equals(delivery.status)) {
                System.out.println("-------------------");
                System.out.println("                       INVOICE                       ");
                System.out.println("--------------------");
                System.out.println("Gas Agency Code: " + agencyCode + "\t\t\tDate: " + invoiceDate);
                System.out.println("Gas Agency Name: " + agencyName + "\t\tAgency Phone No: " + phNumber);
                System.out.println("Gas Connection No.: " + (i + 101) + "\t\tCustomer Name: " + delivery.name);
                System.out.println("Booking Date: " + sdf.format(delivery.bookingDate) + "\tCustomer Mobile No.: " + delivery.mobile);
                System.out.println("Amount: " + delivery.amount);
                System.out.println("Refund: " + delivery.refund);
                System.out.println("Total Amount: " + (delivery.amount));
                System.out.println("Delivery Person Name: " + delivery.delPersonName);
                System.out.println("Delivery Date: " + sdf.format(delivery.deliveryDate));
                System.out.println("----------------------\n\n");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("                    Bharat Gas Agency                     ");
        System.out.println("**********************************************************");

        Delivery[] deliveryObject = new Delivery[6];
        deliveryObject[0] = new Delivery("Aditya", "wardNo 04 Sikta", "westchamparan", "845307", "7061806085", 1);
        deliveryObject[1] = new Delivery("Rahul", "MG Road", "Patna", "800001", "9876543210", 2);
        deliveryObject[2] = new Delivery("Priya", "Kankarbagh", "Patna", "800020", "9123456780", 1);
        deliveryObject[3] = new Delivery("Amit", "Boring Road", "Patna", "800013", "9988776655", 3);
        deliveryObject[4] = new Delivery("Sneha", "Rajendra Nagar", "Muzaffarpur", "842001", "8899001122", 2);
        deliveryObject[5] = new Delivery("Vikash", "Station Road", "Gaya", "823001", "7766554433", 1);

        for (Delivery delivery : deliveryObject) {
            delivery.delPersonDetails();
            delivery.getLastDate();
            delivery.getDates();
            delivery.validate();
            delivery.amountCalc();
            delivery.verifyOtp();
        }

        cylinderCount(deliveryObject);
        checkLateDel(deliveryObject);
        numOfSingleCylinders(deliveryObject);
        DeliveryDetails(deliveryObject);
        printReport(deliveryObject);
        printInvoice(deliveryObject);
    }
}