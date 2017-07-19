package service;

import dao.TopicDao;
import dao.UserDao;
import entity.Resource;
import entity.Topic;
import entity.User;
import entity.Visibility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service
public class TopicService {
    @Autowired
    TopicDao topicDao;
    @Autowired
    UserDao userDao;

    public TopicService(){}

    public Boolean validName(String name, User creator){
        Topic topic=topicDao.findByUserAndTopicName(creator,name);
        if(topic==null){
            return true;
        }
        else{
            return false;
        }
    }

    public String createTopic(String name, String visibility, HttpSession session){
        if(name=="" || name==null){
            return "Topic name cannot be empty";
        }
        if(visibility=="" || visibility==null){
            return "Please Select Privacy of topic";
        }

        User user=userDao.findByUsername((String)session.getAttribute("username"));
        if(validName(name,user)){
            Visibility topicVisibility = Visibility.valueOf(visibility);
            Date date=new Date();
            Topic topic=new Topic(name,user,date,date,topicVisibility);
            topicDao.save(topic,user);
            System.out.println("valid");
            return null;
        }
        else
        {
            return "This topic already created by you";
        }
    }

    public List<Topic> getUserTopic(HttpSession session) {
        return topicDao.topicsSubscribedByUsername((String) session.getAttribute("username"));
    }

    public ModelAndView showtopic(String id, HttpSession session) {
        ModelAndView mv=new ModelAndView("topic");
        Topic topic=topicDao.getById(Integer.parseInt(id));
        int subscriptionCount=topicDao.subscriptionCountOfTopic(topic);
        int postCount=topicDao.postCountOfTopic(topic);
        List<User> subcribers=topicDao.subscribersOfTopic(topic);
        List<Resource> posts=topicDao.postsOfTopic(topic);
        System.out.println("post count = "+postCount);
        mv.addObject("topic",topic);
        mv.addObject("subscriptions",subscriptionCount);
        mv.addObject("posts",postCount);
        mv.addObject("subscribersList",subcribers);
        mv.addObject("postsList",posts);
        return mv;
    }

    //SETTER
    public void setTopicDao(TopicDao topicDoa) {
        this.topicDao = topicDoa;
    }

}
