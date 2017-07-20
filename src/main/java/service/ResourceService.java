package service;

import dao.*;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@Service
public class ResourceService {
    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    LinkResourceDao linkResourceDao;
    @Autowired
    DocumentResourceDao documentResourceDao;
    @Autowired
    ResourceDao resourceDao;

    public ResourceService() {}

    public String createLink(String link, String description, String topic, HttpSession session){
        if(link=="" || link==null){
            return "URL cannot be empty";
        }
        if(description=="" || description==null){
            return "Description cannot be empty";
        }
        if(topic=="" || topic==null){
            return "Please select the topic";
        }
        Date date=new Date();
        User createdBy=userDao.findByUsername((String) session.getAttribute("username"));
        Topic topic1=topicDao.getById(Integer.parseInt(topic));
        System.out.println(topic1);
        LinkResource linkResource=new LinkResource(description,createdBy,topic1,date,date,link);
        System.out.println(linkResource);
        linkResourceDao.save(linkResource);
        return null;
    }

    public String createDocument(CommonsMultipartFile file,String description,String topic, HttpSession session){
        if(file.isEmpty()){
            return "Please Upload the file";
        }
        if(description=="" || description==null){
            return "Description cannot be empty";
        }
        if(topic=="" || topic==null){
            return "Please Select the topic";
        }

        String path=session.getServletContext().getRealPath("/");
        System.out.println("context path is : "+path);
        String filename=file.getOriginalFilename();
        try{
            byte barr[]=file.getBytes();
            System.out.println("file saved at : "+path+"/"+filename);
            String filepath=path+"/"+filename;
            BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(filepath));
            bout.write(barr);
            bout.flush();
            bout.close();
            User creator=userDao.findByUsername((String) session.getAttribute("username"));
            Topic topic1=topicDao.getById(Integer.parseInt(topic));
            Date date=new Date();
            DocumentResource document=new DocumentResource(description,creator,topic1,date,date,filepath);
            documentResourceDao.save(document);
        }
        catch(Exception e){
            System.out.println(e);
            return "Some Error occurred";
        }
        return null;
    }

    public ModelAndView viewpost(String id) {
        Resource resource=resourceDao.getByid(Integer.parseInt(id));
        return new ModelAndView("viewPost","post",resource);
    }

    public List<Resource> nrecentshare(int n) {
        List<Resource> resourceList=resourceDao.nrecentPublicShares(n);
        return resourceList;
    }

    //SETTERS
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setTopicDao(TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public void setLinkResourceDao(LinkResourceDao linkResourceDao) {
        this.linkResourceDao = linkResourceDao;
    }

    public void setDocumentResourceDao(DocumentResourceDao documentResourceDao) {
        this.documentResourceDao = documentResourceDao;
    }

    public void setResourceDao(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

}