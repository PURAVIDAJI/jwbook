package ch06;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceDAO {
  Connection conn = null;
  PreparedStatement pstmt;

  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbook?serverTimezone=Asia/Seoul";

  public void open() {
    try {
      Class.forName(JDBC_DRIVER);
      try {
        conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  //연결하는 부분!

  public void close() {
    try {
      pstmt.close();
      conn.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


  public ProductServiceDAO() {
  }

  //전부가져오기
  public List<Product> findAll() {



    List<Product> products = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement("select * from product");
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        Product p = new Product();
        p.setId(rs.getString("id"));
        p.setName(rs.getString("name"));
        p.setMaker(rs.getString("maker"));
        p.setPrice(rs.getInt("price"));
        p.setDate(rs.getString("date"));
        products.add(p);
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return products;
  }
  //values를 가져오면 항목 가져와 준다.

  public Product find(String id) {

    Product p = new Product();

    try {
      pstmt = conn.prepareStatement("select * from product where id = ?");
      pstmt.setString(1, id);
      ResultSet rs = pstmt.executeQuery();
      rs.next();
      p.setId(rs.getString("id"));
      p.setName(rs.getString("name"));
      p.setMaker(rs.getString("maker"));
      p.setPrice(rs.getInt("price"));
      p.setDate(rs.getString("date"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return p;
  }


  public void insert(Product product) {

    try {
      pstmt = conn.prepareStatement("insert into product(id,name,maker,price,date) values(?,?,?,?,?);");
      pstmt.setString(1,product.getId());
      pstmt.setString(2,product.getName());
      pstmt.setString(3,product.getMaker());
      pstmt.setInt(4,product.getPrice());
      pstmt.setString(5,product.getDate());
      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("등록완료");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    } finally {
      close();
    }
  }

  public void update(String id, int price){
    try {
      pstmt = conn.prepareStatement("update product set price=? where id =? ");
      pstmt.setInt(1,price);
      pstmt.setString(2,id);
      //db에 보낼 쿼리에 이걸 담아서 보내준다!!! 그러니가, set을 쓰는 거야.
      //위에 선언된 쿼리의 ? 부분에 1번재 ?에는 무엇을 보내고,
      //2번째 ?에는 무엇을 보낼지 설정하는 과정이다.

      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("등록완료");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
  public void  delete(String id){
    System.out.println("service.delete 실행중");
    try {
      pstmt = conn.prepareStatement("delete from product where id=?");
      pstmt.setString(1,id);
      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("등록완료");
      }


    } catch (SQLException e) {
      throw new RuntimeException(e);
    }


  }

}




//public void update(String id, int price){
//    Product p = find(id);
//    p.setPrice(price);
//    products.put(id,p);
// }
//
// public void delete(String id){
//   products.remove(id);
// }
//
// public void deleteall(String id){
//    products.clear();
// }
//  public void insert(Product product){
//    products.put(product.getId(), product);
//  }
//
//}


//insert를 넣으면 html에서 컨트롤러로 부탁하고, DAO로 간다음에 데이터베이스를 추가하고, redirect한 다음에,
//뷰 화면에서 누르면 새로운 요청이 가고, 컨트롤러에서 DAO를 뒤져서 처리한 값을 가져오고,
//DAO => 데이터 가져오는 역할