package controller;

import dao.ResourceDao;
import dao.TopicDao;
import entity.Resource;
import entity.Topic;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BaseController extends ParentController {

    @RequestMapping("/")
    ModelAndView homepage(HttpSession session) {
        if(session.getAttribute("username")==null){
            return new ModelAndView("forward:/index");
        }
        else{
            return new ModelAndView("redirect:/dashboard");
        }
    }

    @RequestMapping("/index")
    ModelAndView index(HttpSession session){
        if(session.getAttribute("username")==null){
            ResourceDao resourceDao=new ResourceDao();
            List<Resource> resourceList= resourceDao.nrecentPublicShares(5);
            return new ModelAndView("index","recentshares",resourceList);
        }
        else {
            return new ModelAndView("redirect:/dashboard");
        }
    }

    @RequestMapping("/dashboard")
    ModelAndView dashboard(HttpSession session){
        if(session.getAttribute("username")!=null){
            return new ModelAndView("dashboard");
        }
        else{
            return new ModelAndView("forward:/","error","Login To Continue");
        }
    }

    @RequestMapping("/editprofile")
    ModelAndView editprofile(HttpSession session){
        if(session.getAttribute("username")!=null){
            return new ModelAndView("EditProfile");
        }
        else{
            return new ModelAndView("forward:/","error","Login To Continue");
        }
    }

    @RequestMapping("/users")
    ModelAndView users(){
        return new ModelAndView("allUsers");
    }

}
