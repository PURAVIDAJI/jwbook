package ch08;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class RestApiExample {
    @GET //get으로 요청들어오면 아래의 메소드가 처리해줄게(서블릿이 아님)
    @Produces(MediaType.TEXT_PLAIN)//텍스트로 리턴해줄게
    public String sayHello(){
        return "Heollo API result - text_plain";

    }

    @POST
    @Produces(MediaType.TEXT_HTML)
    public String sayHello(@QueryParam("msg") String msg){
        return msg + " API result - text_html<h1>";

    }
}
