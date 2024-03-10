package dealership;

/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */


import car.Car;
import car.NewCar;
import car.UsedCar;

public class Dealership {

    public static void main(String[] args) {
        Car[] carInventory = new Car[6];

        // Create three NewCar objects and store them in the even indexes of the array
        carInventory[0] = new NewCar("N2312", 2006, 18000, 2000, 3000, 2000);
        carInventory[2] = new NewCar("N6467", 2006, 43000, 4000, 6000, 3000);
        carInventory[4] = new NewCar("N0864", 2007, 24000, 1200, 2500, 0);

        // Create three UsedCar objects and store them in the odd indexes of the array
        carInventory[1] = new UsedCar("U3425", 2003, 16000, 400, 40000, 0.15);
        carInventory[3] = new UsedCar("U2347", 1998, 8000, 700, 12000, 0.1);
        carInventory[5] = new UsedCar("U4739", 2005, 18000, 2400, 12000, 0.2);

        // Update the total assets of NewCar, UsedCar, and Car.
        for (Car car : carInventory) {
            car.updateAssets();
        }

        // Print out the requested data
        System.out.println("Total Assets of Dealer = " + Car.getTotalAssets());
        System.out.println("Total Assets of NewCar = " + NewCar.getNewCarTotalAssets());
        System.out.println("Average New Car Price = " + NewCar.getNewCarTotalAssets() / NewCar.getNewCarCount());
        System.out.println("Total Assets of UsedCar = " + UsedCar.getUsedCarTotalAssets());
        System.out.println("Average Used Car Price = " + 
        		String.format("%.3f", UsedCar.getUsedCarTotalAssets() / UsedCar.getUsedCarCount()) + "\n");

        // Detailed description of each Car including total price and deal quality
        for (Car car : carInventory) {
            if (car instanceof NewCar) {
                NewCar newCar = (NewCar) car;
                System.out.println("New Car");
                System.out.printf(car.toString());
                System.out.println("Total price = " + newCar.computeTotal());
                System.out.println("Deal = " + (newCar.goodBusiness() ? "Good" : "Bad"));
            } else if (car instanceof UsedCar) {
                UsedCar usedCar = (UsedCar) car;
                System.out.println("Used Car");
                System.out.printf(car.toString());
                System.out.println("Total price = " + usedCar.computeTotal());
                System.out.println("Deal = " + (usedCar.goodBusiness() ? "Good" : "Bad"));
            }
            System.out.println(); 
        }
    }
}
