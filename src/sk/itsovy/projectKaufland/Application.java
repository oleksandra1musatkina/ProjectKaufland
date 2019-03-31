package sk.itsovy.projectKaufland;

import sk.itsovy.projectKaufland.bill.Bill;
import sk.itsovy.projectKaufland.items.drinks.Bottle;
import sk.itsovy.projectKaufland.items.drinks.Draft;
import sk.itsovy.projectKaufland.items.drinks.Drink;
import sk.itsovy.projectKaufland.items.foods.Food;
import sk.itsovy.projectKaufland.items.foods.Fruit;
import sk.itsovy.projectKaufland.items.foods.Pastry;
import sk.itsovy.projectKaufland.items.foods.Sweets;
import sk.itsovy.projectKaufland.items.goods.Category;
import sk.itsovy.projectKaufland.items.goods.Goods;

public class Application {

    private Bill bill;

    public Application() {
        bill = new Bill();
        bill.addItem(new Bottle("Mineralka", 1.2, false, 3));
        bill.addItem(new Draft("Cola", 2.3, true, 750));
        bill.addItem(new Pastry("Meno", 0.3, 1));
        bill.addItem(new Sweets("3bit", 0.7, 2));
        bill.addItem(new Fruit("Ananas", 2.4, 1));
        bill.addItem(new Goods("Mydlo", 1.2, 3, Category.HYGIENE));
        bill.addItem(new Pastry("pizza", 1.10, 1));
        bill.addItem(new Food ("jablko", 0.79);
        bill.addItem(new Goods("ceruzka", 1.30, 4,Category.SCHOOL));
        bill.addItem(new Draft ("vinea", 0.90, true, 0.3));
        bill.addItem(new Bottle("BirellLemon", 2.40, true, 2));
        bill.removeItem( Bottle "BirellLemon");

// max 7 poloziek v blocku (vyvolat exeption : billIsFull)
        bill.printBill();
        System.out.println("total price: " + bill.countPrice());
    }
}
