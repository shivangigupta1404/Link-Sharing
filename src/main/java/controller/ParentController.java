package controller;

import entity.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import service.TopicService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ParentController {

    @Autowired
    TopicService topicService;

   @ModelAttribute
    public void addingObject(HttpSession session, Model model){
       if(session.getAttribute("username")!=null) {
           List<Topic> topicList = topicService.getUserTopic(session);
           if (topicList.isEmpty()) {
               model.addAttribute("topicOption", "NONE");
           } else {
               String result = "<option value=''>Select Topic</option>";
               for (Topic t : topicList) {
                   String option = "<option value=" + t.getId() + ">" + t.getName() + "</option>";
                   result = result + option;
               }
               System.out.println("result is: " + result);
               model.addAttribute("topicOption", result);
           }
       }
    }
}
