package net.usermd.mcichon;

import net.usermd.mcichon.body.ClientServer.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        LOGGER.info("main method was started");

        Main main = new Main();
        main.init();

        LOGGER.info("main method was ended");
    }
}
