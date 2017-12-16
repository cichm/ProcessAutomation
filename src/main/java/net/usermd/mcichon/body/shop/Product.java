package net.usermd.mcichon.body.shop;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

  public String name;
  public Price price;
  public Weight weight;

  public Product(String name, Price price, Weight weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }
}