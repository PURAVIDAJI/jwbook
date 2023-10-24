package ch07;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NewsDAO {
  Connection conn = null;
  PreparedStatement pstmt;

  final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
  final String JDBC_URL = "jdbc:mysql://localhost:3306/jwbook?serverTimezone=Asia/Seoul";

  public Connection open() {
    Connection conn = null;
    try {
      Class.forName(JDBC_DRIVER);
      try {
        conn = DriverManager.getConnection(JDBC_URL, "root", "1111");
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    } finally {
      System.out.println("연결완료...");
    }
    return conn;
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


  public NewsDAO() {
  }

  //전부가져오기
  public List<News> getAll() throws SQLException {
    Connection conn =open();
    List<News> newsList = new ArrayList<>();
    PreparedStatement pstmt =null;
    pstmt = conn.prepareStatement("select aid,title, date_format(date,'%Y-%m-%d %h-%m-%s') as cdate from news");
    ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {
        //데이터 오브젝(bin)에 내가 넣고 싶은 데이터를 매핑해서 사용한다.
        //
        News news = new News();
        //스튜던트와 똑같은 구조의 아이들 넣어준다
//        BeanUtils.populate(s, rs.get);

        news.setAid(rs.getInt("aid"));
        news.setTitle(rs.getString("title"));
        news.setDate(rs.getString("cdate"));

        newsList.add(news);
      }
      pstmt.close();
      rs.close();
      conn.close();
    return newsList;
  }
  //values를 가져오면 항목 가져와 준다.

  public News getNews(int aid) throws SQLException {
    Connection conn = open();
    News n = new News();
    PreparedStatement pstmt =null;

    pstmt = conn.prepareStatement("select aid,title,img, date_format(date,'%Y-%m-%d %h-%m-%s') as cdate,content from news where aid =?");
    pstmt.setInt(1, aid);
    ResultSet rs = pstmt.executeQuery();

      rs.next();

      n.setAid(rs.getInt("aid"));
      n.setTitle(rs.getString("title"));
      n.setImg(rs.getString("img"));
      n.setDate(rs.getString("cdate"));
      n.setContent(rs.getString("content"));

      pstmt.close();
      rs.close();
      conn.close();
      return n;
  }


  public void addNews(News news) throws SQLException{

    Connection conn =open();
    PreparedStatement pstmt;

      pstmt = conn.prepareStatement("insert into news(title,img,date,content)"+
          "values(?,?,CURRENT_TIMESTAMP(),?);");
      pstmt.setString(1,news.getTitle());
      pstmt.setString(2,news.getImg());
      pstmt.setString(3, news.getContent());
      int res = pstmt.executeUpdate();
      if(res ==1){
        System.out.println("등록완료");
      }

      pstmt.close();
      conn.close();

  }

//  public void update(int id, String univ, String birth, String email){
//    try {
//      pstmt = conn.prepareStatement("update student set univ =?, birth=?, email=? where id =? ");
//      pstmt.setString(1,univ);
//      pstmt.setString(2,birth);
//      pstmt.setString(3,email);
//      pstmt.setInt(4,id);
//
//      //db에 보낼 쿼리에 이걸 담아서 보내준다!!! 그러니가, set을 쓰는 거야.
//      //위에 선언된 쿼리의 ? 부분에 1번재 ?에는 무엇을 보내고,
//      //2번째 ?에는 무엇을 보낼지 설정하는 과정이다.
//
//      int res = pstmt.executeUpdate();
//      if(res ==1){
//        System.out.println("등록완료");
//      }
//
//    } catch (SQLException e) {
//      throw new RuntimeException(e);
//    }
//
//  }
  public void delNews(int aid) throws SQLException{
    Connection conn =open();
    System.out.println("service.delete 실행중");
    PreparedStatement pstmt = conn.prepareStatement("delete from news where aid=?");
      pstmt.setInt(1,aid);
      int res = pstmt.executeUpdate();

      if(res ==0){
        throw new SQLException("NEWS 삭제 오류");


    }
      pstmt.close();
      conn.close();
    }
}



//insert를 넣으면 html에서 컨트롤러로 부탁하고, DAO로 간다음에 데이터베이스를 추가하고, redirect한 다음에,
//뷰 화면에서 누르면 새로운 요청이 가고, 컨트롤러에서 DAO를 뒤져서 처리한 값을 가져오고,
//DAO => 데이터 가져오는 역할