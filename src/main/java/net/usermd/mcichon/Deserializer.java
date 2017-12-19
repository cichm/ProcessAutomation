package net.usermd.mcichon;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.usermd.mcichon.body.shop.Order;

import java.io.IOException;

public class Deserializer {
    private final ObjectMapper mapper;

    public Deserializer(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Order readOrderLinesFrom(String json) {
        try {
            return mapper.readValue(json, Order.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Order();
    }

    public String serialize(Order orders) {
        try {
            return mapper.writeValueAsString(orders);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
