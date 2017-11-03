package net.usermd.mcichon.body.shop;

public class Product {

  public final String name;
  public final Price price;
  public final Weight weight;

  public Product(String name, Price price, Weight weight) {
    this.name = name;
    this.price = price;
    this.weight = weight;
  }
}