package service;

import dao.ResourceDao;
import dao.TopicDao;
import entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public class SearchService {

    @Autowired
    TopicDao topicDao;
    @Autowired
    ResourceDao resourceDao;



    public List<Resource> searchResource(String text) {
        if(text!=""){
            System.out.println("text is present");
            List<Resource> resourceList=resourceDao.searchPublicByText(text);
            return resourceList;
        }
        else{
            return null;
        }
    }
}
