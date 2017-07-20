package controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import dao.ResourceDao;
import entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.ResourceService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ResourceController extends ParentController {
    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/createlink",method = RequestMethod.POST)
    ModelAndView createLink(@RequestParam String link, @RequestParam String description, @RequestParam String topic, HttpSession session){
       String result=resourceService.createLink(link,description,topic,session);
       if(result==null){
           return new ModelAndView("dashboard","success","Url has been shared");
       }
       else{
           return new ModelAndView("dashboard","error",result);
       }
    }

    @RequestMapping(value="/createdocument",method = RequestMethod.POST)
    ModelAndView createdocument(@RequestParam CommonsMultipartFile file,@RequestParam String description,@RequestParam String topic,HttpSession session){
        String result=resourceService.createDocument(file,description,topic,session);
        if(result==null){
            return new ModelAndView("dashboard","success","Document created successFully!");
        }
        else{
            return new ModelAndView("dashboard","error",result);
        }
    }

    @ResponseBody
    @RequestMapping(value="/recentshare")
    List<Resource> nrecentshare(){
        return resourceService.nrecentshare(5);
    }

    @RequestMapping(value="/viewpost/{id}")
    ModelAndView viewpost(@PathVariable String id){
        return resourceService.viewpost(id);
    }

    //TO prevent site from crashing if user accidently hit the url
    @RequestMapping(value={"/createlink","/createdocument"},method= RequestMethod.GET)
    ModelAndView dummy(){
        return new ModelAndView("redirect:/dashboard");
    }

    //SETTER
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }
}
