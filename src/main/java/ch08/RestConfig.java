package ch08;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.Map;

@ApplicationPath("/api") //톰캣에 api관련 서버 등록하기
public class RestConfig extends Application {
    public Map<String, Object> getProperties(){
        Map<String,Object> properties = new HashMap<>();
        properties.put("jersey.config.server.provider.packages","ch08"); //설정파일을 넣는 것

        return properties;
    }
}
