package net.usermd.mcichon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.usermd.mcichon.body.shop.OrderLines;

import java.io.IOException;

public class Deserializer {
    private final ObjectMapper mapper;

    public Deserializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    OrderLines readOrderLinesFrom(String json) {
        try {
            return mapper.readValue(json, OrderLines.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OrderLines();
    }

    public String serialize(OrderLines orders) {
        try {
            return mapper.writeValueAsString(orders);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
