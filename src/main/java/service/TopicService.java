package service;

import dao.SubscriptionDao;
import dao.TopicDao;
import dao.UserDao;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;


@Service
public class TopicService {
    @Autowired
    TopicDao topicDao;
    @Autowired
    UserDao userDao;
    @Autowired
    SubscriptionDao subscriptionDao;

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


    public ModelAndView subscribe(String id,String seriousness, HttpSession session) {
        if(session.getAttribute("username")==null){
            return new ModelAndView("forward:/","error","Login to subscribe");
        }
        if(seriousness=="" || seriousness==null)
            return new ModelAndView("forward:/showtopic/"+id,"error","Select Seriousness");
        Topic topic=topicDao.getById(Integer.parseInt(id));
        User user=userDao.findByUsername((String) session.getAttribute("username"));
        Date date=new Date();
        Seriousness seriousness1=Seriousness.valueOf(seriousness);
        Subscription subscription=new Subscription(topic,user,seriousness1,date);
        subscriptionDao.save(subscription);
        return new ModelAndView("forward:/showtopic/"+id,"success","Subscribed!");
    }
    //SETTER
    public void setTopicDao(TopicDao topicDoa) {
        this.topicDao = topicDoa;
    }


}
