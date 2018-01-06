package net.usermd.mcichon.body.generateProducts;

import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.deserialize.Deserializer;
import net.usermd.mcichon.body.products.Order;
import net.usermd.mcichon.body.products.OrderItem;
import net.usermd.mcichon.body.products.OrderLine;
import net.usermd.mcichon.body.products.Product;
import net.usermd.mcichon.body.promotion.RoundBonus;
import net.usermd.mcichon.body.promotion.SetPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static net.usermd.mcichon.body.products.Price.price;
import static net.usermd.mcichon.body.products.Weight.weight;

@AllArgsConstructor
public class Europe {
    public Deserializer deserializer;
    private static final Logger LOGGER = LoggerFactory.getLogger(Europe.class);

    public String generateItems() {
        LOGGER.info("generateItems method was started");

        final int RADIUS = 5;
        RoundBonus roundHole = new RoundBonus(RADIUS);

        List<OrderLine> productsList = Arrays.asList(
                new OrderLine(new OrderItem(new Product("Ziemniaki", weight(2.5)), price(2.5)), 1),
                new OrderLine(new OrderItem(new Product("Szczypiorek", weight(4.2)), price(2.4)), 1),
                new OrderLine(new OrderItem(new Product("Słownik", weight(4.2)), price(14)), 1),
                new OrderLine(new OrderItem(new Product("Ołówek", weight(0.2)), price(0.8)), 2),
                new OrderLine(new OrderItem(new Product("Drukarka", weight(12)), price(100)), 4),
                new OrderLine(new OrderItem(new Product("Kredki", weight(0.4)), price(3.2)), 3),
                new OrderLine(new OrderItem(new Product("Portfel", weight(1.2)), price(8)), 1),
                new OrderLine(new OrderItem(new Product("Tablet", weight(3.2)), price(300)), 2),
                new OrderLine(new OrderItem(new Product("Juice", weight(0.7)), price(1.8)), 1),
                new OrderLine(new OrderItem(new Product("Woda", weight(1.8)), price(0.7)), 1),
                new OrderLine(new OrderItem(new Product("Słuchawki", weight(0.4)), price(16)), 3),
                new OrderLine(new OrderItem(new Product("Chleb", weight(1.8)), price(2.4)), 4),
                new OrderLine(new OrderItem(new Product("Długopis", weight(0.2)), price(2.2)), 4),
                new OrderLine(new OrderItem(new Product("Pasta do zębów", weight(0.5)), price(3.5)), 2),
                new OrderLine(new OrderItem(new Product("Szczoteczka do zębów", weight(0.3)), price(9.5)), 3),
                new OrderLine(new OrderItem(new Product("Książka", weight(3.3)), price(16.0)), 1),
                new OrderLine(new OrderItem(new Product("Jogurt", weight(0.8)), price(2.2)), 1),
                new OrderLine(new OrderItem(new Product("Sól kuchenna", weight(0.8)), price(2.6)), 2),
                new OrderLine(new OrderItem(new Product("Skarpetki", weight(0.3)), price(3.0)), 3),
                new OrderLine(new OrderItem(new Product("Telefon", weight(1.2)), price(400)), 2),
                new OrderLine(new OrderItem(new Product("Pendrive", weight(0.6)), price(40)), 1),
                new OrderLine(new OrderItem(new Product("Banany", weight(3.6)), price(3.2)), 4),
                new OrderLine(new OrderItem(new Product("Jabłka", weight(3.2)), price(2.8)), 1),
                new OrderLine(new OrderItem(new Product("Computer", weight(16)), price(SetPrice.initialize(roundHole, 4000))), 3),
                new OrderLine(new OrderItem(new Product("Ipod", weight(3.6)), price(SetPrice.initialize(roundHole, 160))), 3)
        );

        LOGGER.info("generateItems method was ended");

        return deserializer.serialize(new Order(productsList));
    }
}

