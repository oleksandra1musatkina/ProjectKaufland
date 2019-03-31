package sk.itsovy.projectKaufland.items.food;

import sk.itsovy.projectKaufland.items.Item;

public abstract class Food extends Item {

    private int calories;

    public Food(String name, double price, int calories) {
        super(name, price);
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
