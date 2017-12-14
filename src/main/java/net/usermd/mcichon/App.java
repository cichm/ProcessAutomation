package net.usermd.mcichon;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;

import io.rsocket.util.DefaultPayload;
import net.usermd.mcichon.body.shop.OrderLine;
import net.usermd.mcichon.body.shop.Price;
import net.usermd.mcichon.body.shop.Product;
import net.usermd.mcichon.body.shop.Weight;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

import static net.functional.library.common.CollectionUtilities.foldLeft;
import static net.functional.library.common.CollectionUtilities.list;
import static net.usermd.mcichon.body.shop.Price.price;
import static net.usermd.mcichon.body.shop.Weight.weight;

public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("main method was started");

        RSocketFactory.receive()
                .acceptor(
                        (setupPayload, reactiveSocket) ->
                                Mono.just(
                                        new AbstractRSocket() {
                                            @Override
                                            public Mono<Payload> requestResponse(Payload p) {
                                                return Mono.just(p);
                                            }
                                        }))
                .transport(TcpServerTransport.create("localhost", 7000))
                .start()
                .subscribe();

        RSocket socket =
                RSocketFactory.connect()
                        .transport(TcpClientTransport.create("localhost", 7000))
                        .start()
                        .block();

        App app = new App();
        String items = app.generateItems();

        socket
                .requestResponse(DefaultPayload.create("Hello"))
                .map(Payload::getDataUtf8)
                .onErrorReturn("error")
                .doOnNext(System.out::println)
                .block();

        socket.close().block();
    }

    public String generateItems() {
        LOGGER.info("generateItems method was started");

        ObjectMapper mapper = new ObjectMapper();

        Product potatoes = new Product("Ziemniaki", price(3.6), weight(4.2));
        Product dictonary = new Product("Słownik", price(14), weight(4.2));
        Product pencil = new Product("Ołówek", price(0.8), weight(0.2));
        Product printer = new Product("Drukarka", price(100), weight(12));
        Product pencils = new Product("Kredki", price(3.2), weight(0.4));
        Product wallet = new Product("Portfel", price(8), weight(1.2));
        Product tablet = new Product("Tablet", price(300), weight(3.2));
        Product juice = new Product("Juice", price(1.8), weight(0.7));
        Product water = new Product("Woda", price(0.7), weight(1.8));
        Product headphones = new Product("Słuchawki", price(16), weight(0.4));
        Product bread = new Product("Chleb", price(2.4), weight(1.8));
        Product pen = new Product("Długopis", price(2.2), weight(0.2));
        Product toothPaste = new Product("Pasta do zębów", price(3.5), weight(0.5));
        Product toothBrush = new Product("Szczoteczka do zębów", price(9.5), weight(0.3));
        Product book = new Product("Książka", price(16.0), weight(3.3));
        Product yogurt = new Product("Jogurt", price(2.2), weight(0.8));
        Product salt = new Product("Sól kuchenna", price(2.6), weight(0.8));
        Product socks = new Product("Skarpetki", price(3.0), weight(0.3));
        Product phone = new Product("Telefon", price(400), weight(1.2));
        Product pendrive = new Product("Pendrive", price(40), weight(0.6));
        Product computer = new Product("Computer", price(1400), weight(16));
        Product bananas = new Product("Banany", price(3.2), weight(3.6));
        Product apples = new Product("Jabłka", price(2.8), weight(3.2));
        Product ipod = new Product("Ipod", price(160), weight(3.6));

        List<OrderLine> order = list(
                new OrderLine(potatoes, 15),
                new OrderLine(dictonary, 6),
                new OrderLine(pencil, 16),
                new OrderLine(printer, 6),
                new OrderLine(pencils, 14),
                new OrderLine(wallet, 3),
                new OrderLine(tablet, 6),
                new OrderLine(juice, 16),
                new OrderLine(water, 16),
                new OrderLine(headphones, 4),
                new OrderLine(bread, 72),
                new OrderLine(pen, 16),
                new OrderLine(toothPaste, 8),
                new OrderLine(toothBrush, 12),
                new OrderLine(book, 4),
                new OrderLine(yogurt, 16),
                new OrderLine(salt, 12),
                new OrderLine(socks, 8),
                new OrderLine(phone, 8),
                new OrderLine(pendrive, 13),
                new OrderLine(computer, 6),
                new OrderLine(bananas, 20),
                new OrderLine(apples, 22),
                new OrderLine(ipod, 8)
        );

        Price price = foldLeft(order, Price.ZERO, Price.sum);
        LOGGER.info("~> Sum price: " + price);

        Weight weight = foldLeft(order, Weight.ZERO, Weight.sum);
        LOGGER.info("~> Sum weight: " + weight);

        String details = null;
        try {
            details = mapper.writeValueAsString(order);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO: remove this
        // example: json String to object
        List<OrderLine> myObjects = null;

        try {
            myObjects = mapper.readValue(
                    details,
                    mapper.getTypeFactory().constructParametricType(List.class, Object.class)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("generateItems method was ended");

        return details;
    }
}
