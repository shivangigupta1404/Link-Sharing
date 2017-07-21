package controller;

import entity.Resource;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.SearchService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping(value = "/search/offline/")
    @ResponseBody
    void searchOffline(@RequestParam String text, HttpServletResponse response) throws IOException {
        ObjectMapper mapperObj = new ObjectMapper();
        List<Resource> resourceList=searchService.searchResource(text);
        for(Resource R:resourceList){
            try {
                String jsonStr = mapperObj.writeValueAsString(R);
                response.getWriter().write(jsonStr);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
