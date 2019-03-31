package sk.itsovy.projectKaufland.items.food;

import sk.itsovy.projectKaufland.items.PiecesInterface;

public class Pastry extends Food implements PiecesInterface {
    private int amount;

    public Pastry(String name, double price, int calories, int amount) {
        super(name, price, calories);
        this.amount = amount;
    }

    public Pastry(String name, double price, int amount) {
        this(name, price, -1, amount);
    }


    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public double getTotalPrice() {
        return amount * getPrice();
    }
}
