package controller;

import com.sun.deploy.net.HttpResponse;
import entity.Topic;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.TopicService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import entity.Visibility;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class TopicController extends ParentController {
    @Autowired
    TopicService topicService;

    
    @RequestMapping(value="/createTopic",method= RequestMethod.POST)
    ModelAndView createTopic(@RequestParam String name, @RequestParam String visibility, HttpSession session){
        String result=topicService.createTopic(name,visibility,session);
        if(result==null){
            return new ModelAndView("dashboard","success","Topic Created");
        }
        else{
            return new ModelAndView("dashboard","error",result);
        }
    }

    //TO prevent site from crashing if user accidently hit the url
    @RequestMapping(value="/createTopic",method= RequestMethod.GET)
    ModelAndView dummy(){
        return new ModelAndView("redirect:/dashboard");
    }

    @RequestMapping(value="/showtopic/{id}")
    ModelAndView showtopic(@PathVariable String id,HttpSession session) {
        return topicService.showtopic(id,session);
    }


    //Setter
    public void setTopicService(TopicService topicService) {
        this.topicService = topicService;
    }

}
