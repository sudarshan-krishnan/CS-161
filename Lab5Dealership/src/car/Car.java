package car;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */


public abstract class Car {
    private String id;
    private int year;
    protected double basePrice;
    protected double commission;
    protected static double totalAssets = 0;
    
    //Constructor that sets each of the fields to the provided variables
    public Car(String id, int year, double price, double commission) {
        this.id = id;
        this.year = year;
        this.basePrice = price;
        this.commission = commission;
    }

  //Getter for TOTAL_ASSETS
    public static double getTotalAssets() {
        return totalAssets;
    }

    @Override
    public String toString() {
        return "Vehicle ID = " + id + "\n" +
               "Model year = " + year + "\n" +
               "Base price = " + basePrice + "\n" +
               "Commission = " + commission + "\n";
    }

    public abstract void updateAssets();

    public abstract double getMileage();

    public abstract boolean goodBusiness();
}
