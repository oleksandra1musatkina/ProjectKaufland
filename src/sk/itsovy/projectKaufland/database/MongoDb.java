package sk.itsovy.projectKaufland.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import sk.itsovy.projectKaufland.bill.Bill;
import org.bson.Document;
import sk.itsovy.projectKaufland.items.Item;
import sk.itsovy.projectKaufland.items.PiecesInterface;
import sk.itsovy.projectKaufland.items.drink.DraftInterface;
import sk.itsovy.projectKaufland.items.food.Fruit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MongoDb {

    public void insertNewBill(Bill bill) throws IOException {
//        MongoClient mongoClient = MongoClients.create();
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        List<Item> items = bill.getItems();
        Document doc = new Document("date", bill.getDatetime());
        doc.append("number", bill.getTodayBillNumber());
        List<Document> itemsDocuments = new ArrayList<>();
        for (Item item : items) {
            Document itemDocument = new Document("name", item.getName());
            itemDocument.append("price", item.getPrice());
            if (item instanceof PiecesInterface) {
                itemDocument.append("pcs", ((PiecesInterface) item).getAmount());
            } else if (item instanceof Fruit) {
                itemDocument.append("g", ((Fruit) item).getWeight());
            } else if (item instanceof DraftInterface) {
                itemDocument.append("l", ((DraftInterface) item).getVolume());
            }

            itemsDocuments.add(itemDocument);
        }
        doc.append("items", itemsDocuments);
        doc.append("price", new Document("EUR", bill.getFinalPrice()).append("USD", bill.getFinalPriceInUSD()));
        MongoDatabase kaufland = mongoClient.getDatabase("kaufland");
        MongoCollection<Document> collection = kaufland.getCollection("bill");
        collection.insertOne(doc);


    }

}
