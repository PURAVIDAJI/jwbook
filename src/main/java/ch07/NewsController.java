package ch07;

import ch06.Student;
import ch06.StudentService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize = 1024*1024*2, location = "c:/temp/img")


public class NewsController extends HttpServlet {
  //생성될때 productService 정보가 생성되게 해야함

  private NewsDAO newsDAO;
  private ServletContext ctx;
  //웹 리소스 기본 경로 지정
  private final String START_PAGE = "/ch07/newsList.jsp";

  @Override
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    newsDAO = new NewsDAO();
    ctx = getServletContext();
  }


  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setCharacterEncoding("utf-8");
    String action = req.getParameter("action");

    newsDAO = new NewsDAO();

    Method m;
    String view = null;

    if (action == null) {
      action = "list";
    } else {
      try {
        m = this.getClass().getMethod(action, HttpServletRequest.class, HttpServletResponse.class);
        view = (String) m.invoke(this, req, resp);
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
    if (view.startsWith("redirect:/")) {
      view = view.substring("redirect:/".length()); //redirect빼고 그 뒤의 것 가져와서 다시 저장
      resp.sendRedirect(view);

    } else {
      ctx.getRequestDispatcher("/ch07/" + view).forward(req, resp);
    }
  }


  public String addNews(HttpServletRequest req, HttpServletResponse resp) {
    News news = new News();
    try {
      Part file = req.getPart("file");
      String fileName = file.getSubmittedFileName();
      if (fileName != null && !fileName.isEmpty()) {
        file.write(fileName);
      } //파일 처리 부분

      BeanUtils.populate(news, req.getParameterMap()); //다른 속성 매핑해서 넣어줌
      news.setImg("/img/" + fileName);
      newsDAO.addNews(news);

    } catch (Exception e) {
      e.printStackTrace();
    }
    return "redirect:/news.nhn?action=list";
  }


  public String list(HttpServletRequest req, HttpServletResponse resp) {
    List<News> list;
    try {
      list = newsDAO.getAll();
      req.setAttribute("newsList", list); //데이터를 jsp에게 전달
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return "newsList.jsp";
  }

    public String deleteNews(HttpServletRequest req, HttpServletResponse resp){
      try {
        newsDAO.delNews(Integer.parseInt(req.getParameter("aid")));
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
      return "redirect:/news.nhn?action=list";
    }

  public String getNews(HttpServletRequest req, HttpServletResponse resp) {
    try {
      News news = newsDAO.getNews(Integer.parseInt(req.getParameter("aid")));
      req.setAttribute("news",news);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return "newsView.jsp";
  }
}