package net.usermd.mcichon.db;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.products.Order;
import org.bson.Document;
import pl.khuzzuk.messaging.Bus;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DatabaseDumper {
    private Bus bus;
    private MongoDatabase mongoDatabase;

    public void init() {
        bus.setReaction("save", this::saveToDB);
    }

    private void saveToDB(Order order) {
        MongoCollection<Document> collection = mongoDatabase.getCollection("products");

        List<Document> documents = convertToDocuments(order);
        collection.insertMany(documents);
    }

    static List<Document> convertToDocuments(Order order) {
        return order.getOrderLines().stream()
                    .map(orderLine1 -> new Document()
                            .append("product", orderLine1.getOrderItem().getProduct().getName())
                            .append("weight", orderLine1.getOrderItem().getProduct().getWeight().getValue())
                            .append("price", orderLine1.getOrderItem().getPrice().getValue()))
                    .collect(Collectors.toList());
    }
}
