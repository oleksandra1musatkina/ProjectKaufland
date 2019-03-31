package sk.itsovy.projectKaufland.items.drinks;

import sk.itsovy.projectKaufland.items.Piece;

public class Bottle extends Drink implements Piece {
    private int amount;

    public Bottle(String name, double price, boolean sweet, int amount) {
        super(name, price, sweet);
        this.amount = amount;
    }

//    public Bottle(String name, double price,int amount) {
//
//        this.(name, price = false.amount);
//    }
//
//    public Bottle(String name, double price, boolean sweet) {
//
//        this.(name,price = sweet.amount);
//    }

    @Override
    public int getAmount() {
        return amount;
    }
}
