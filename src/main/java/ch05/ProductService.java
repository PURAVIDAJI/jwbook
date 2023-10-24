package ch05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductService {
  Map<String, Product> products = new HashMap<>();

  public ProductService() {
    Product p = new Product("101", "Apple 12", "Apple", 1200000, "2020.12.12");
    products.put("101", p);
    p = new Product("102", "SamSung Galaxy", "SamSung", 1300000, "2021.2.2");
    products.put("102", p);
    p = new Product("103", "LG 100", "LG", 1000000, "2021.3.2");
    products.put("103", p);

  }

  //전부가져오기
  public List<Product> findAll() {
    return new ArrayList<>(products.values());
  }
  //values를 가져오면 항목 가져와 준다.

  public Product find(String id) {
    return products.get(id);
  }

public void update(String id, int price){
    Product p = find(id);
    p.setPrice(price);
    products.put(id,p);
 }

 public void delete(String id){
   products.remove(id);
 }

 public void deleteall(String id){
    products.clear();
 }
  public void insert(Product product){
    products.put(product.getId(), product);
  }

}


//insert를 넣으면 html에서 컨트롤러로 부탁하고, DAO로 간다음에 데이터베이스를 추가하고, redirect한 다음에,
//뷰 화면에서 누르면 새로운 요청이 가고, 컨트롤러에서 DAO를 뒤져서 처리한 값을 가져오고,
//DAO => 데이터 가져오는 역할