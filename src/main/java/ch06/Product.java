package ch06;

public class Product {
  private String id;
  private String name;
  private String maker;
  private int price;
  private String date;

  public Product() {

  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getMaker() {
    return maker;
  }

  public int getPrice() {
    return price;
  }

  public String getDate() {
    return date;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setMaker(String maker) {
    this.maker = maker;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setDate(String date) {
    this.date = date;
  }
}
