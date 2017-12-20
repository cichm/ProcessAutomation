package net.usermd.mcichon.body.deserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import net.usermd.mcichon.body.products.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@AllArgsConstructor
public class Deserializer {

    private final ObjectMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(Deserializer.class);

    public Order readOrderLinesFrom(String json) {

        LOGGER.info("readOrderLinesFrom method was started");

        try {
            return mapper.readValue(json, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("readOrderLinesFrom method was ended");

        return new Order();
    }

    public String serialize(Order orders) {

        LOGGER.info("serialize method was started");

        try {
            return mapper.writeValueAsString(orders);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        LOGGER.info("serialize method was ended");

        return "";
    }
}
