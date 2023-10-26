package ch07;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/ListenerTest") //ListenerTest가 호출되면 속성이 추가되거나 삭제되거나 할때 감지하는 것!!!!

public class ListenerTestServlet extends HttpServlet {

    ServletContext sc;
    @Override
    public void init(ServletConfig config) throws ServletException {
        //사용하고 싶은 servletContext를 가져옴
        super.init(config);
        sc=getServletContext();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        sc.setAttribute("name","홍길동"); //속성을 하나 추가한 것' 서블릿컨텍스트의 이름 설정
        HttpSession s = req.getSession(); //요청된 파일의 세션있으면 가져와서 저장해둠. 없으면 만들어짐
        s.setAttribute("name","hong"); //세션의 속성을 추가함
        s.setAttribute("id", s.getId()); //세션의 원래 아이디 가져와서 속성에 넣음...

    }

}
