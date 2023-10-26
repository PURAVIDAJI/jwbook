package ch07;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("*.nhn")

//여러개의 요청 중에서 원하는 것에만 필터를 적용할래~
public class EncodingFilter implements Filter {


    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
            throws IOException, ServletException {
        //Http통신방식이 아닌 곳에서도 매개변수 적용가능
        //하지만 우리는 http서블릿리퀘스트가 필요하니까 형변환을 해야함.
        HttpServletRequest httpReq = (HttpServletRequest)req;

        //앞으로 추가되는 애의 경우에
        //메서드가 post이면 utf-8방식으로 읽음
        //get방식은 안가져오니까, 원래 있었던 애의 경우는 바뀌지 않는다!

        if(httpReq.getMethod().equalsIgnoreCase("POST") ||
                httpReq.getMethod().equalsIgnoreCase("GET")){
            req.setCharacterEncoding("utf-8");
        }
        fc.doFilter(req,resp);
        //서블릿에 전달하는 역할도 함.!!!!chain 방식으로 뒤로 전달전달


    }
}
