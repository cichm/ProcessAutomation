package net.usermd.mcichon.body.generateProducts;

import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.convertCurrency.ConverterPln;
import net.usermd.mcichon.body.convertCurrency.IConverter;
import net.usermd.mcichon.body.deserialize.Deserializer;
import net.usermd.mcichon.body.dynamicproxy.BenchmarkTimingDynamicInvocationHandler;
import net.usermd.mcichon.body.products.Order;
import net.usermd.mcichon.body.products.OrderItem;
import net.usermd.mcichon.body.products.OrderLine;
import net.usermd.mcichon.body.products.Product;
import net.usermd.mcichon.body.promotion.RoundBonus;
import net.usermd.mcichon.body.promotion.SetPrice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        IConverter iConverter = new ConverterPln();

        List<OrderLine> productsList = Arrays.asList(
                new OrderLine(new OrderItem(new Product("Ziemniaki",            weight(2.5)),   price(iConverter.convert(1.5D))), 1),
                new OrderLine(new OrderItem(new Product("Szczypiorek",          weight(4.2)),   price(iConverter.convert(1.4D))), 1),
                new OrderLine(new OrderItem(new Product("Słownik",              weight(4.2)),   price(iConverter.convert(7.D))), 1),
                new OrderLine(new OrderItem(new Product("Ołówek",               weight(0.2)),   price(iConverter.convert(0.5D))), 1),
                new OrderLine(new OrderItem(new Product("Drukarka",             weight(12)),    price(iConverter.convert(50.0D))), 1),
                new OrderLine(new OrderItem(new Product("Kredki",               weight(0.4)),   price(iConverter.convert(2.1D))), 1),
                new OrderLine(new OrderItem(new Product("Portfel",              weight(1.2)),   price(iConverter.convert(3.5D))), 1),
                new OrderLine(new OrderItem(new Product("Tablet",               weight(3.2)),   price(iConverter.convert(100.5D))), 1),
                new OrderLine(new OrderItem(new Product("Juice",                weight(0.7)),   price(iConverter.convert(0.9D))), 1),
                new OrderLine(new OrderItem(new Product("Woda",                 weight(1.8)),   price(iConverter.convert(0.5D))), 1),
                new OrderLine(new OrderItem(new Product("Słuchawki",            weight(0.4)),   price(iConverter.convert(4.0D))), 1),
                new OrderLine(new OrderItem(new Product("Chleb",                weight(1.8)),   price(iConverter.convert(1.3D))), 1),
                new OrderLine(new OrderItem(new Product("Długopis",             weight(0.2)),   price(iConverter.convert(1.5D))), 1),
                new OrderLine(new OrderItem(new Product("Pasta do zębów",       weight(0.5)),   price(iConverter.convert(1.0D))), 1),
                new OrderLine(new OrderItem(new Product("Szczoteczka do zębów", weight(0.3)),   price(iConverter.convert(3.6D))), 1),
                new OrderLine(new OrderItem(new Product("Książka",              weight(3.3)),   price(iConverter.convert(4.0D))), 1),
                new OrderLine(new OrderItem(new Product("Jogurt",               weight(0.8)),   price(iConverter.convert(1.1D))), 1),
                new OrderLine(new OrderItem(new Product("Sól kuchenna",         weight(0.8)),   price(iConverter.convert(1.2D))), 1),
                new OrderLine(new OrderItem(new Product("Skarpetki",            weight(0.3)),   price(iConverter.convert(1.0D))), 1),
                new OrderLine(new OrderItem(new Product("Telefon",              weight(1.2)),   price(iConverter.convert(100.0D))), 1),
                new OrderLine(new OrderItem(new Product("Pendrive",             weight(0.6)),   price(iConverter.convert(10.0D))), 1),
                new OrderLine(new OrderItem(new Product("Banany",               weight(3.6)),   price(iConverter.convert(1.6D))), 1),
                new OrderLine(new OrderItem(new Product("Jabłka",               weight(3.2)),   price(iConverter.convert(1.0D))), 1),
                new OrderLine(new OrderItem(new Product("Computer",             weight(16)),    price(SetPrice.initialize(roundHole, iConverter.convert(1500.0D)))), 1),
                new OrderLine(new OrderItem(new Product("Ipod",                 weight(3.6)),   price(SetPrice.initialize(roundHole, iConverter.convert(80.0D)))), 1)
        );

        Map mapProxyInstance = (Map) Proxy.newProxyInstance(
                Europe.class.getClassLoader(),
                new Class[] { Map.class },
                new BenchmarkTimingDynamicInvocationHandler(new HashMap<>())
        );

        mapProxyInstance.put("Europe", productsList);

        LOGGER.info("generateItems method was ended");

        return deserializer.serialize(new Order(productsList));
    }
}

