package car;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

public class NewCar extends Car {
    private double optionsCost;
    private double rebate;
    private static int newCarCount = 0;
    private static double newCarTotalAssets = 0;

    public NewCar(String id, int year, double price, double commission, double options, double rebate) {
        super(id, year, price, commission);
        this.optionsCost = options;
        this.rebate = rebate;
        newCarCount++;
    }

    public static double getNewCarTotalAssets() {
        return newCarTotalAssets;
    }

    public static int getNewCarCount() {
        return newCarCount;
    }

    @Override
    public String toString() {
        return super.toString() +
               "Option cost = " + optionsCost + "\n" +
               "Rebate = " + rebate + "\n";
    }

    @Override
    public void updateAssets() {
        double totalCost = computeTotal();
        Car.totalAssets += totalCost;
        newCarTotalAssets += totalCost;
    }

    @Override
    public double getMileage() {
        return 0;
    }

    @Override
    public boolean goodBusiness() {
        return commission > 0.08 * basePrice;
    }

    public double computeTotal() {
        return basePrice + optionsCost + commission - rebate;
    }
}
