package sk.itsovy.projectKaufland.main;

import sk.itsovy.projectKaufland.bill.Bill;
import sk.itsovy.projectKaufland.exception.BillException;
import sk.itsovy.projectKaufland.items.drink.Bottle;
import sk.itsovy.projectKaufland.items.drink.Draft;
import sk.itsovy.projectKaufland.items.food.Fruit;
import sk.itsovy.projectKaufland.items.food.Pastry;
import sk.itsovy.projectKaufland.items.food.Sweets;
import sk.itsovy.projectKaufland.items.goods.Category;
import sk.itsovy.projectKaufland.items.goods.Goods;
import sk.itsovy.projectKaufland.xml.XmlGenerator;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;

public class Application {

    private Bill bill;

    public void example() throws BillException, IOException, TransformerException, ParserConfigurationException, SQLException {
        bill = new Bill();
        Bottle mineralka = new Bottle("Mineralka", 1.2, 3);
        bill.addItem(mineralka);
        Pastry gazdovska = new Pastry("Gazdovska", 0.3, 111, 2);
        bill.addItem(gazdovska);
        Draft cola = new Draft("Cola", 2.3, true, 750);
        bill.addItem(cola);
        Sweets triBit = new Sweets("3bit", 0.7, 2);
        bill.addItem(triBit);
        Fruit ananas = new Fruit("Ananas", 2.4, 1);
        bill.addItem(ananas);
        Goods mydlo = new Goods("Mydlo", 1.2, 3, Category.HYGIENE);
        bill.addItem(mydlo);
        bill.removeItem(mydlo);
        bill.print();
        XmlGenerator xmlGenerator = new XmlGenerator();
        xmlGenerator.generateXml(bill);
        bill.end();
//        System.out.println("total price: " + bill.getFinalPrice());
    }
}
