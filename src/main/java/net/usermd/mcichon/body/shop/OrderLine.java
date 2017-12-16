package net.usermd.mcichon.body.shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class OrderLine {

  private final Product product;
  private final int count;

  public OrderLine(Product product, int count) {
    this.product = product;
    this.count = count;
  }

  @JsonIgnore
  public Weight getWeight() {
    return this.product.weight.mult(this.count);
  }

  @JsonIgnore
  public Price getAmount() {
    return this.product.price.mult(this.count);
  }
}
