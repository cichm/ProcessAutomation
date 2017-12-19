package net.usermd.mcichon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.DefaultPayload;
import net.usermd.mcichon.body.products.*;
import net.usermd.mcichon.db.DatabaseDumper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.khuzzuk.messaging.Bus;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static net.usermd.mcichon.body.products.Price.price;
import static net.usermd.mcichon.body.products.Weight.weight;

public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private Deserializer deserializer;
    private static Bus bus;

    private App(Deserializer deserializer) {
        this.deserializer = deserializer;
    }

    public static void main(String[] args) {
        LOGGER.info("main method was started");
        bus = Bus.initializeBus(false);

        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase mongoDatabase = mongo.getDatabase("ProductsDatabase");
        new DatabaseDumper(bus, mongoDatabase).init();

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

        App app = new App(new Deserializer(new ObjectMapper()));

        assert null != socket;
        socket
                .requestResponse(DefaultPayload.create(app.generateItems()))
                .map(Payload::getDataUtf8)
                .map(json -> app.deserializer.readOrderLinesFrom(json))
                .onErrorReturn(new Order())
                .doOnNext(orderLines -> bus.send("save", orderLines))
                .block();

        socket.close().block();
    }

    private String generateItems() {
        LOGGER.info("generateItems method was started");

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
                new OrderLine(new OrderItem(new Product("Computer", weight(16)), price(1400)), 3),
                new OrderLine(new OrderItem(new Product("Banany", weight(3.6)), price(3.2)), 4),
                new OrderLine(new OrderItem(new Product("Jabłka", weight(3.2)), price(2.8)), 1),
                new OrderLine(new OrderItem(new Product("Ipod", weight(3.6)), price(160)), 3)
        );

        LOGGER.info("generateItems method was ended");

        return deserializer.serialize(new Order(productsList));
    }
}
