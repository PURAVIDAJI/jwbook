package ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pcontrol") //hello 접속시 아래가 실행되는거야~!

public class ProductController extends HttpServlet {
  //생성될때 productService 정보가 생성되게 해야함
  ProductServiceDAO service;

  @Override
  public void init() throws ServletException {
    service = new ProductServiceDAO();
    service.open();
  }

  @Override
  public void destroy() {
    service.open();
  }

  public ProductController(){

    
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
        getServletContext().getRequestDispatcher("/ch05/" + view)
            .forward(req, resp);

      } else if (action.equals("info")) {
        view = info(req, resp);
        getServletContext().getRequestDispatcher("/ch05/" + view)
            .forward(req, resp);
      } else if (action.equals("update")) {


      }else if(action.equals("insert")){
        view = info(req,resp);
        resp.sendRedirect(view);

      } else if (action.equals("delete")) {

      }

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

  private String update(HttpServletRequest req, HttpServletResponse resp){
    String method =req.getMethod();
    if(method.equals("GET")){
      req.setAttribute("p",service.find(req.getParameter("id")));
      return "productForm.jsp";
    } else if (method.equals("POST")) {
      service.update(req.getParameter("id"),Integer.parseInt(req.getParameter("price")));
      req.setAttribute("p",service.find(req.getParameter("id")));
      return "productInfo.jsp";

    }else {
      return null;
    }
  }
  private String insert(HttpServletRequest req, HttpServletResponse resp) {
    String method =req.getMethod();
    if(method.equals("GET")){
      return "newProduct.jsp";

    }else if(method.equals("POST")){
      Product p = new Product();
      p.setId(req.getParameter("id"));
      p.setName(req.getParameter("name"));
      p.setMaker(req.getParameter("maker"));
      p.setPrice(Integer.parseInt(req.getParameter("price")));
      p.setDate(req.getParameter("date"));
      service.insert(p);
      return "/pcontrol?action=list";
    } else {
      return null;

    }

  }


  private String delete(HttpServletRequest req, HttpServletResponse resp){
    service.delete(req.getParameter("id"));
    return "/pcontrol?action=list";
  }
}
