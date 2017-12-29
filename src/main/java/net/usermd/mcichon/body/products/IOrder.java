package net.usermd.mcichon.body.products;

import java.util.List;

public interface IOrder {
    List<OrderLine> getProducts();
}
