package ch05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/pcontrol") //hello 접속시 아래가 실행되는거야~!

public class ProductController extends HttpServlet {
  //생성될때 productService 정보가 생성되게 해야함
  ProductService service;
  public ProductController(){
    service = new ProductService();
    
  }
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //로직구현 -> 사용자가 원한 경로에따라
    String action = req.getParameter("action");
    String view = "";
    if (action == null) {
      getServletContext().getRequestDispatcher("/pcontrol?action=list")
          .forward(req, resp);
    } else {
      if (action.equals("list")) {
        view = list(req, resp);


      } else if (action.equals("info")) {
        view = info(req, resp);
      } else if (action.equals("update")) {

      }else if(action.equals("insert")){

      } else if (action.equals("delete")) {

      }

      getServletContext().getRequestDispatcher("/ch05/" + view)
        .forward(req, resp);
  }
  }
  private String list(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("products",service.findAll());
    //리스트로 반환된것 넘기겠다.
    
    return "productList.jsp"; //데이터를 넣어서 어느화면으로 넘겨주면 될지 알려주는 함수
    
  }
  
  private String info(HttpServletRequest req, HttpServletResponse resp){
    req.setAttribute("p",service.find(req.getParameter("id")));
    return "productInfo.jsp";
    
  }
}
