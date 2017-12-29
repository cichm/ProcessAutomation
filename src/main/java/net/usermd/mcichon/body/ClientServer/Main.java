package net.usermd.mcichon.body.ClientServer;

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
import lombok.NoArgsConstructor;
import net.usermd.mcichon.body.deserialize.Deserializer;
import net.usermd.mcichon.body.generateProducts.Europe;
import net.usermd.mcichon.body.products.Order;
import net.usermd.mcichon.db.DatabaseDumper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.khuzzuk.messaging.Bus;
import reactor.core.publisher.Mono;

@NoArgsConstructor
public class Main {

    private static Bus bus;
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public void init() {

        LOGGER.info("init method was started");

        bus = Bus.initializeBus(false);

        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        MongoDatabase mongoDatabase = mongoClient.getDatabase("ProductsDatabase");
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

        Europe europe = new Europe(new Deserializer(new ObjectMapper()));

        assert null != socket;
        socket
                .requestResponse(DefaultPayload.create(europe.generateItems()))
                .map(Payload::getDataUtf8)
                .map(json -> europe.deserializer.readOrderLinesFrom(json))
                .onErrorReturn(new Order())
                .doOnNext(orderLines -> bus.send("save", orderLines))
                .block();

        LOGGER.info("init method was ended");
    }
}
