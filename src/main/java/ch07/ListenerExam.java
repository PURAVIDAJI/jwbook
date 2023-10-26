package ch07;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
//세션 서버단
//쿠키 클라이언트단에 저장
//session.setAttribute("설정한 세션아이디", 세션에 넣을 값);  -> 세션 유지하고 싶은 시간도 설정할 수 있음
//DB의 사용자 정보와 일치하는 로그인 정보가 들어오면 사용자의 정보를 세션에 저장함.




public class ListenerExam implements ServletContextListener, ServletContextAttributeListener,
        HttpSessionListener, HttpSessionAttributeListener,
        ServletRequestListener, ServletRequestAttributeListener {
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {
        event.getServletContext().log("ServletContext 속성이 추가됨" + event.getValue());
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
       sce.getServletContext().log("ServletContext가 시작됨.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().log("ServletContext가 종료됨.");
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        event.getSession().getServletContext().log("Session의 속성 추가 :" +
                event.getValue());
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().getServletContext().log("Session이 생성됨 :" +
                se.getSession().getId());
    }
}
