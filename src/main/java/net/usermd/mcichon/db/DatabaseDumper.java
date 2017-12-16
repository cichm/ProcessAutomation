package net.usermd.mcichon.db;


import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.shop.OrderLines;
import pl.khuzzuk.messaging.Bus;

@AllArgsConstructor
public class DatabaseDumper {
    private Bus bus;

    public void init() {
        bus.setReaction("save", this::saveToDB);
    }

    private void saveToDB(OrderLines orderLines) {
        //TODO implement this
    }
}
