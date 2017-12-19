package net.usermd.mcichon.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.shop.Order;
import net.usermd.mcichon.body.shop.OrderLine;
import org.bson.Document;
import pl.khuzzuk.messaging.Bus;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DatabaseDumper {
    private Bus bus;

    public void init() {
        bus.setReaction("save", this::saveToDB);
    }

    private void saveToDB(Order order) {
        List<OrderLine> orderLine = order.getOrderLines();

        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("myDb");
        MongoCollection<Document> collection = database.getCollection("sampleCollection");

        List <Document> productLists = new ArrayList <> ();
        for (OrderLine anOrderLine : orderLine) {
            productLists.add(new Document()
                    .append("product", anOrderLine.getOrderItem().getProduct().getName())
                    .append("weight", anOrderLine.getOrderItem().getProduct().getWeight().getValue())
                    .append("price", anOrderLine.getOrderItem().getPrice().getValue()));
        }

        collection.insertMany(productLists);
    }
}
