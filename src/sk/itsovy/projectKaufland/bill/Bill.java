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
    private int todayBillNumber;


    public Bill() {
        items = new ArrayList<>();
        open = true;
        count = 0;
        todayBillNumber = 0;
    }

    public int getTodayBillNumber() {
        return todayBillNumber;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void end() throws SQLException {
        if (open) {
//            System.out.println(datum + " " + time);
            System.out.println("dt: " + datetime);
            Database database = Database.getInstance();
            todayBillNumber = database.getTodayBillsCount() + 1;
            database.insertNewBill(this);
        }
        open = false;
    }

    public void addItem(Item item) throws BillException {
        if (item != null) {

            boolean contains = false;
            for (Item oldItem : items) {

                if (oldItem.getName().compareToIgnoreCase(oldItem.getName()) == 0 &&
                        item.getClass().isInstance(oldItem) &&
                        item.getPrice() == oldItem.getPrice()
                ) {
                    System.out.println("rovnake");
                    contains = true;
                    update(oldItem, item);
                }
            }
            if (!contains) {

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

    public void update(Item item1, Item item2) {
        if (item1 instanceof DraftInterface) {
            ((DraftInterface) item1).setVolume(((DraftInterface) item1).getVolume() + ((DraftInterface) item2).getVolume());
        } else if (item1 instanceof Fruit) {
            ((Fruit) item1).setWeight(((Fruit) item1).getWeight() + ((Fruit) item2).getWeight());
        } else if (item1 instanceof PiecesInterface) {
            ((PiecesInterface) item1).setAmount(((PiecesInterface) item1).getAmount() + ((PiecesInterface) item2).getAmount());
        }
    }

}
