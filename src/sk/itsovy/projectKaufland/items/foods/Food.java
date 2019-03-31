package sk.itsovy.projectKaufland.items.foods;

import sk.itsovy.projectKaufland.items.Item;

public abstract class Food extends Item {

    private int calories;

    public Food(String name, double price) {
        super(name, price);
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
