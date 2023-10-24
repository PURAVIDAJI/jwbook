package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/forward") //hello 접속시 아래가 실행되는거야~!

public class ForwardServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("sharedInfo","전달된 데이터");
    req.getRequestDispatcher("/ch04/forward.jsp").forward(req, resp);
  }
  //내가 받은 요청 다른이에게 넘김 -forward
  //redirect = 내가 정보를 다 처리해서 알려줌

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }

}
