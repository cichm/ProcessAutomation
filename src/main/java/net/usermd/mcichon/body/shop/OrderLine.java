package net.usermd.mcichon.body.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLine {
    private OrderItem orderItem;
    private int quantity;
}
