package net.usermd.mcichon.body.products;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements IOrder {
    private List<OrderLine> orderLines;

    @JsonIgnore
    private static IOrder iOrder;

    Order(IOrder iOrder) {
        this.iOrder = iOrder;
    }

    @Override
    @JsonIgnore
    public List<OrderLine> getProducts() {
        return orderLines;
    }
}
