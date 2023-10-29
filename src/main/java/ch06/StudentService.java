package ch06;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
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


  public StudentService() {
  }

  //전부가져오기
  public List<Student> findAll() {


    List<Student> students = new ArrayList<>();
    try {
      pstmt = conn.prepareStatement("select * from student");
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        //데이터 오브젝(bin)에 내가 넣고 싶은 데이터를 매핑해서 사용한다.
        //
        Student s = new Student();
        //스튜던트와 똑같은 구조의 아이들 넣어준다
//        BeanUtils.populate(s, rs.get);

        s.setId(rs.getInt("id"));
        s.setName(rs.getString("name"));
        s.setUniv(rs.getString("univ"));
        s.setBirth(rs.getString("birth"));
        s.setEmail(rs.getString("email"));
        students.add(s);
        //rs를 이용해 Student 객체에 데이터를 매핑하고, List에 추가한 다음, rs에 담긴 모든 데이터를 실행하고 나서,
        //List 타입을 리턴함
      }
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return students;
  }
  //values를 가져오면 항목 가져와 준다.

  public Student find(int id) {

    Student s = new Student();

    try {
      pstmt = conn.prepareStatement("select * from student where id = ?");
      pstmt.setInt(1, id);
      ResultSet rs = pstmt.executeQuery();

      rs.next();
      s.setId(rs.getInt("id"));
      s.setName(rs.getString("name"));
      s.setUniv(rs.getString("univ"));
      s.setBirth(rs.getString("birth"));
      s.setEmail(rs.getString("email"));

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

    return s;
  }


  public void insert(Student student) {
    //student객체를 인자로 받아와서, SQL문과 매핑하기

    try {
      pstmt = conn.prepareStatement("insert into student(id,name,univ,birth,email) values(?,?,?,?,?);");
      pstmt.setInt(1,student.getId());
      pstmt.setString(2,student.getName());
      pstmt.setString(3,student.getUniv());
      pstmt.setString(4, student.getBirth());
      pstmt.setString(5,student.getEmail());
      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("새로운 등록 완료");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public void update(int id, String univ, String birth, String email){
    try {
      pstmt = conn.prepareStatement("update student set univ =?, birth=?, email=? where id =? ");
      pstmt.setString(1,univ);
      pstmt.setString(2,birth);
      pstmt.setString(3,email);
      pstmt.setInt(4,id);

      //db에 보낼 쿼리에 이걸 담아서 보내준다!!! 그러니가, set을 쓰는 거야.
      //위에 선언된 쿼리의 ? 부분에 1번재 ?에는 무엇을 보내고,
      //2번째 ?에는 무엇을 보낼지 설정하는 과정이다.

      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("업데이트 완료");
      }

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }

  }
  public void  delete(String id){
    System.out.println("service.delete 실행중");
    try {
      pstmt = conn.prepareStatement("delete from student where id=?");
      pstmt.setString(1,id);
      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("삭제 완료");
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