package ch05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/pcontrol")

public class ProductController extends HttpServlet {
  //생성될때 productService 정보가 생성되게 해야함
  ProductService service;
  public ProductController(){
    service = new ProductService();

    //서블릿 처음에 생성될 때, 생성자로 ProductService 한 번만 만들어 놓는다.
    // 그 이후에는 사용자 요청이 일어날 때마다 service()메서드만 호출됨.

  }
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //클라이언트 요청 구분하고 처리 메서드를 호출한 다음 뷰로 이동하는 구조 작성
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
