package sk.itsovy.projectKaufland.items.drinks;

public class Draft extends Drink implements DraftInterface {

    private double volume;

    public Draft(String name, double price, boolean sweet, double volume) {
        super(name, price, sweet);
        this.volume = volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public double getVolume() {
        return volume;
}
//    @Override
//    public double getTotalPrice() {
//        return volume*getPrice();
//    }
//    @Override
//    public double getName() {
//        return getName();
//    }
}
