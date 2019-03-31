package sk.itsovy.projectKaufland.items.goods;

import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.Piece;

public class Goods extends Item implements Piece {
    private int amount;
    private Category type;

    public Goods(String name, double price, int amount, Category type) {
        super(name, price);
        this.amount = amount;
        this.type = type;
    }

    public Category getType() {
        return type;
    }

    public void setType(Category type) {
        this.type = type;
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
        return volume*getPrice();
    }
}
