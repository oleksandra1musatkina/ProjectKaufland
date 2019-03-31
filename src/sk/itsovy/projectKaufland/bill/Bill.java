package sk.itsovy.projectKaufland.bill;

import sk.itsovy.projectKaufland.database.Database;
import sk.itsovy.projectKaufland.main.Globals;
import sk.itsovy.projectKaufland.exception.BillException;
import sk.itsovy.projectKaufland.items.drink.DraftInterface;
import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.PiecesInterface;
import sk.itsovy.projectKaufland.items.food.Fruit;
import sk.itsovy.projectKaufland.main.Internet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bill {
    private List<Item> items;
    private int count;
    private boolean open;
    private Date datetime = new Date();


    public Bill() {
        items = new ArrayList<>();
        open = true;
        count = 0;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void end() throws SQLException {
        if (open) {
//            System.out.println(datum + " " + time);
            System.out.println("dt: " + datetime);
            Database database = Database.getInstance();
            database.insertNewBill(this);
        }
        open = false;
    }

    public void addItem(Item item) throws BillException {
        if (item != null) {
            if (open == false) {
                String message = "Bill is closed. Is not allowed to add any items!";
                throw new BillException(message);
            }
            if (count == Globals.MAXITEMS) {
                String message = "Bill is full, maximum is " + Globals.MAXITEMS + " items";
                throw new BillException(message);
            }
            items.add(item);
            count++;
        }
    }


    public void removeItem(Item item) {
        if (items.contains(item)) {
            items.remove(item);
            count--;
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public double getFinalPrice() {
        double finalPrice = 0;
        for (Item item : items) {
            finalPrice += item.getTotalPrice();
        }
        return finalPrice;
    }

    public int getCount() {
        return count;
    }

    public double getFinalPriceInUSD() throws IOException {
        return Internet.getUSDRate() * getFinalPrice();
    }

    public void print() {
        if (count == 0)
            System.out.println("Nothing to print. Bill is empty !");
        else {
            for (Item item : items) {
                if (item instanceof DraftInterface) {
                    System.out.print(item.getName() + " " + ((DraftInterface) item).getVolume() + " ");
                    System.out.println(item.getPrice() + " " + item.getTotalPrice());
                } else if (item instanceof Fruit) {
                    System.out.print(item.getName() + " " + ((Fruit) item).getWeight() + " ");
                    System.out.println(item.getPrice() + " " + item.getTotalPrice());
                } else if (item instanceof PiecesInterface) {
                    System.out.print(item.getName() + " " + ((PiecesInterface) item).getAmount() + " ");
                    System.out.println(item.getPrice() + " " + item.getTotalPrice());
                }

            }
        }
    }
}
