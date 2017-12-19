package net.usermd.mcichon.body.shop;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
    private Product product;
    private Price price;
}
