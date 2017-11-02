package usermd.mcichon;

import io.rsocket.AbstractRSocket;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.RSocketFactory;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.transport.netty.server.TcpServerTransport;
import io.rsocket.util.PayloadImpl;
import reactor.core.publisher.Mono;

public final class App {

  public static void main(String[] args) {
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

        socket
            .requestResponse(new PayloadImpl("Hello"))
            .map(Payload::getDataUtf8)
            .onErrorReturn("error")
            .doOnNext(System.out::println)
            .block();

    socket.close().block();
  }
}
