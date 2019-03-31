package sk.itsovy.projectKaufland.items.drink;

import sk.itsovy.projectKaufland.items.Item;

public abstract class Drink extends Item {
    private boolean sweet;

    public Drink(String name, double price, boolean sweet) {
        super(name, price);
        this.sweet = sweet;
    }
}
