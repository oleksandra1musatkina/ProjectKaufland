package sk.itsovy.projectKaufland.bill;

import sk.itsovy.projectKaufland.items.drinks.DraftInterface;
import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.Piece;
import sk.itsovy.projectKaufland.goods.Globals.MAXITEMS;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private List<Item> items;

    public Bill() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public double countPrice() {
        double totalPrice = 0;
        for (Item item : items) {
            if (item instanceof DraftInterface) {
                double vol = ((DraftInterface) item).getVolume() / 1000;
                totalPrice += vol * item.getPrice();
            } else if (item instanceof Piece) {
                int amount = ((Piece) item).getAmount();
                totalPrice += item.getPrice() * amount;
            } else {
                totalPrice += item.getPrice();
            }
        }
        return totalPrice;
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public void printBill() {
        for (Item item : items) {
            if (item instanceof DraftInterface) {
                System.out.println("name: " + item.getName() + ", price: " + item.getPrice() + ",  volume: " + ((DraftInterface) item).getVolume());
            } else if (item instanceof Piece) {
                System.out.println("name: " + item.getName() + ", price: " + item.getPrice() + ",  amount: " + ((Piece) item).getAmount());
            } else {
                System.out.println("name: " + item.getName() + ", price: " + item.getPrice());
            }
        }
    }
//    public void addItem(){
//        if(count>7) //7 globalna premenna
//
//    }
//    public getCount(double count){
//
//    }
//    public void printItems(){
//        if (count=0);
//    }
    // sucet vsetkych poloziek
    // end premenna typu boolean - ked je uzavreta tak open false , tak ked pridam polozku tak vyhodi exetions
    // metoda and - blocek bude uzatvoreny, ked sa niekto pokusi pridat zavola sa vznimka ,a ked neni yiadna poloyka tak vzpise ye je praydna
}
