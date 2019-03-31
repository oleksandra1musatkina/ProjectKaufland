package sk.itsovy.projectKaufland.items.foods;

import sk.itsovy.projectKaufland.items.Piece;

public class Pastry extends Food implements Piece {
    private int amount;

    public Pastry(String name, double price, int amount) {
        super(name, price);
        this.amount = amount;
    }

//    public Pastry(String name, double price, int amount,double calories) {
//        this.(name,price,-1,amount) = amount;
//    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int getAmount() {
        return amount;
    }
}
