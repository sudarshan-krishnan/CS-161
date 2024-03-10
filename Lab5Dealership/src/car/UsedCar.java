package car;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

public class UsedCar extends Car {
    private double mileage;
    private double rateOfDepreciation;
    private static int usedCarCount = 0;
    private static double usedCarTotalAssets = 0;

    //Constructor that sets each of the fields to the provided variables
    
    public UsedCar(String id, int year, double price, double commission, double mileage, double depRate) {
        super(id, year, price, commission);
        this.mileage = mileage;
        this.rateOfDepreciation = depRate;
        usedCarCount++;
    }

    //Getter for usedCarTotalAssets
    public static double getUsedCarTotalAssets() {
        return usedCarTotalAssets;
    }
    
    //Getter for usedCarCount
    public static int getUsedCarCount() {
        return usedCarCount;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Mileage = " + mileage + "\n";
    }

    @Override
    public void updateAssets() {
        double totalCost = computeTotal();
        Car.totalAssets += totalCost;
        usedCarTotalAssets += totalCost;
    }

    @Override
    public double getMileage() {
        return mileage;
    }

    @Override
    public boolean goodBusiness() {
        return commission > 0.04 * computeTotal();
    }

    public double computeTotal() {
        return basePrice - (mileage * rateOfDepreciation) + commission;
    }
}
