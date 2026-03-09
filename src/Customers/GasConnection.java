package Customers;

import java.text.SimpleDateFormat;
import java.util.*;

public class GasConnection extends Customer {

    protected int numberOfCylinders;
    protected Date lastDate = null;
    static int connectionNumber = 100;

    // Increment connectionNumber for each new connection
    {
        connectionNumber += 1;
    }

    // Constructor
    public GasConnection(String name, String street, String area, String pincode, String mobile, int numberOfCylinders) {
        super(name, street, area, pincode, mobile);
        this.numberOfCylinders = numberOfCylinders;
    }

    // Getter
    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }

    // Method to input last booking date
    public void getLastDate() {
        System.out.println("Enter the last booking date (dd/MM/yyyy):");
        String dateInput = new Scanner(System.in).nextLine();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            lastDate = dateFormat.parse(dateInput);
        } catch (Exception e) {
            System.out.println("Error in getLastDate: " + e);
        }
    }
}