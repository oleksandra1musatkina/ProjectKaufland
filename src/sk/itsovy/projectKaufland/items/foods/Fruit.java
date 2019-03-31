package sk.itsovy.projectKaufland.items.foods;

public class Fruit extends Food {

    private double weight;

    public Fruit(String name, double price, double weight) {
        super(name, price);
        this.weight = weight;
    }
    //    public Pastry(String name, double price, double weight,double calories) {
//        this.(name,price,-1,amount) = amount;
//    }
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
