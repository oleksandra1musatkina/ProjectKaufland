package sk.itsovy.projectKaufland.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import sk.itsovy.projectKaufland.bill.Bill;
import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.PiecesInterface;
import sk.itsovy.projectKaufland.items.drink.DraftInterface;
import sk.itsovy.projectKaufland.items.food.Fruit;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlGenerator {

    public void generateXml(Bill bill) throws ParserConfigurationException, TransformerException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        Element rootElement = doc.createElement("bill");
        doc.appendChild(rootElement);

        Element datum = doc.createElement("date");
        rootElement.appendChild(datum);

        Element datum1 = doc.createElement("dateOfPurchase");
        datum1.appendChild(doc.createTextNode(String.valueOf(bill.getDatetime())));
        datum.appendChild(datum1);

        Element items = doc.createElement("items");
        rootElement.appendChild(items);

//        Database database = new Database();
//        database.getAllPeople();


        for (Item aaa : bill.getItems()) {
            Element item = doc.createElement("item");
            items.appendChild(item);

            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(aaa.getName()));
            item.appendChild(name);

            Element price = doc.createElement("price");
            price.appendChild(doc.createTextNode(String.valueOf(aaa.getPrice())));
            item.appendChild(price);

            Element amount = doc.createElement("amount");
            if (aaa instanceof DraftInterface) {
                amount.appendChild(doc.createTextNode(String.valueOf(((DraftInterface) aaa).getVolume())));
                item.appendChild(amount);
            } else if (aaa instanceof Fruit) {
                amount.appendChild(doc.createTextNode(String.valueOf(((Fruit) aaa).getWeight())));
                item.appendChild(amount);
            } else if (aaa instanceof PiecesInterface) {
                amount.appendChild(doc.createTextNode(String.valueOf(((PiecesInterface) aaa).getAmount())));
                item.appendChild(amount);
            }

            Element unit = doc.createElement("unit");
            if (item instanceof DraftInterface) {
                unit.appendChild(doc.createTextNode("l"));
                item.appendChild(unit);
            } else if (item instanceof Fruit) {
                unit.appendChild(doc.createTextNode("kg"));
                item.appendChild(unit);
            } else if (item instanceof PiecesInterface) {
                unit.appendChild(doc.createTextNode("pcs"));
                item.appendChild(unit);
            }

        }

        Element price = doc.createElement("price");
        rootElement.appendChild(price);

        Element priceEur = doc.createElement("EUR");
        priceEur.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPrice())));
        price.appendChild(priceEur);

        Element priceDol = doc.createElement("USD");
        priceDol.appendChild(doc.createTextNode(String.valueOf(bill.getFinalPriceInUSD())));
        price.appendChild(priceDol);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File("bill.xml"));
        transformer.transform(source, result);

        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
    }
}
