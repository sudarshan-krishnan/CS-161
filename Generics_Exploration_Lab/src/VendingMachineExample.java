/**
 * Name: Sudarshan Krishnan
 * Username: kriss03
 */

public class VendingMachineExample {

    public static void main(String[] args) {
        VendingMachine<Chips> chipsMachine = new VendingMachine<>();
        chipsMachine.load(new Chips());
        VendingMachine<Chocolate> chocolateMachine = new VendingMachine<>();
        chocolateMachine.load(new Chocolate());

        System.out.println("Dispensed Chips: " + chipsMachine.dispense().getFlavor());
        System.out.println("Dispensed Chocolate: " + chocolateMachine.dispense().getCalories());
    }
}

class VendingMachine<T extends Food> {
    private T item;

    public void load(T item) {
        this.item = item;
    }

    public T dispense() {
        return item;
    }
}

abstract class Food {
    public abstract String getCalories();
}

class Chips extends Food {
    @Override
    public String getCalories() {
        return "200 calories";
    }

    public String getFlavor() {
        return "Salty";
    }
}

class Chocolate extends Food {
    @Override
    public String getCalories() {
        return "250 calories";
    }
}
