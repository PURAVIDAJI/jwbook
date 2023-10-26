package ch08;

import ch07.News;
import ch07.NewsDAO;
import lombok.Getter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/news")
public class NewsApiService {

    NewsDAO newsDAO;
    public NewsApiService() {
        newsDAO = new NewsDAO();

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addNews(News news){
        String result="";
        try {
            newsDAO.addNews(news);
            result ="news api : 뉴스 등록 성공";
        } catch (SQLException e) {
            e.printStackTrace();
            result="news api :  뉴스 등록 실패";
        }
        return result;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<News> list(){
        List<News> newsList =null;
        try {
            newsList = newsDAO.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    @GET
    @Path("{aid}")
    @Produces(MediaType.APPLICATION_JSON)
    public News getNews(@PathParam("aid") int aid){
        News news =null;
        try {
            news = newsDAO.getNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return news;
    }

    @DELETE
    @Path("{aid}")
    public String deleteNews(@PathParam("aid") int aid){
        try {
            newsDAO.delNews(aid);
        } catch (SQLException e) {
            e.printStackTrace();
            return "News API : 뉴스 삭제 실패!-"+aid;
        }
        return "News API: 뉴스 삭제됨! - "+aid;
    }


}
