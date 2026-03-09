package gasSupplier;

public interface gasAgency {

    // constants
    public static final String agencyName = "Bharat Gas";
    public static final int agencyCode = 1234;
    public static final int phNumber = 706170;

    // default method to display agency info
    default void agencyDisplay() {
        System.out.println("The agency name is: " + agencyName);
        System.out.println("The agency code is: " + agencyCode);
        System.out.println("The agency phone number is: " + phNumber);
    }
}