package ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@WebServlet("/student")

public class StudentController extends HttpServlet {
  //생성될때 productService 정보가 생성되게 해야함
  StudentService service;

  @Override
  public void init() throws ServletException {
    service = new StudentService();
    service.open();
  }

  @Override
  public void destroy() {
    service.close();
  }

  public StudentController(){

    
  }
  
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //로직구현 -> 사용자가 원한 경로에따라
    String action = req.getParameter("action");
    String method = req.getMethod();
    String view = "";
    if (action == null) {
      getServletContext().getRequestDispatcher("/student?action=list")
          .forward(req, resp);
    }else {
      switch (action){
        case "list" :
          view = list(req,resp);
          getServletContext().getRequestDispatcher("/ch06/" + view)
                  .forward(req, resp);

          break;
        case "update" : view = update(req,resp);
          if(method.equals("GET"))
            getServletContext().getRequestDispatcher("/ch06/" + view)
                    .forward(req, resp);
          else if (method.equals("POST")) {

            resp.sendRedirect(view);
          }
          break;
        case "delete" : view = delete(req,resp);break;
        case "insert" :
          view = insert(req,resp);
          resp.sendRedirect(view);

          break;
      }
    }
  }
  private String list(HttpServletRequest req, HttpServletResponse resp) {

    List<Student> students = service.findAll();
    req.setAttribute("students", service.findAll());
    //리스트로 반환된것 넘기겠다.

    return "studentList.jsp"; //데이터를 넣어서 어느화면으로 넘겨주면 될지 알려주는 함수

  }

  private String update(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
    req.setCharacterEncoding("UTF-8");
    resp.setContentType("text/html; charset=UTF-8");
    String method =req.getMethod();
    if(method.equals("GET")){
      req.setAttribute("s",service.find(Integer.parseInt(req.getParameter("id"))));
      return "studentForm.jsp";
    } else if (method.equals("POST")) {
      req.setCharacterEncoding("utf-8");
      service.update(Integer.parseInt(req.getParameter("id")),req.getParameter("univ"),req.getParameter("birth"),req.getParameter("email"));
      req.setAttribute("s",service.find(Integer.parseInt(req.getParameter("id"))));
      return "/student?action=list";

    }else {
      return null;
    }
  }
  private String insert(HttpServletRequest req, HttpServletResponse resp) {
    
    String method =req.getMethod();
   if(method.equals("POST")){
      Student s = new Student();
      s.setId(Integer.parseInt(req.getParameter("id")));
      s.setName(req.getParameter("name"));
      s.setUniv(req.getParameter("univ"));
      s.setBirth(req.getParameter("birth"));
      s.setEmail(req.getParameter("email"));
      service.insert(s);
      return "/student?action=list";
    } else {
      return null;

    }

  }


  private String delete(HttpServletRequest req, HttpServletResponse resp){
    service.delete(req.getParameter("id"));
    return "/student?action=list";
  }
}
