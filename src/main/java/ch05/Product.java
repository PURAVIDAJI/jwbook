package ch05;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
  private String id;
  private String name;
  private String maker;
  private int price;
  private String date;

  public Product(String id, String name, String maker, int price, String date) {
    this.id = id;
    this.name = name;
    this.maker = maker;
    this.price = price;
    this.date = date;
  }
}
