package service;

import dao.ResourceDao;
import dao.TopicDao;
import dao.UserDao;
import dto.TopicDto;
import dto.UserDto;
import entity.Resource;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    TopicDao topicDao;
    @Autowired
    ResourceDao resourceDao;

    public Boolean checkEmailAvailability(String email){
        Boolean exist=userDao.checkEmailAvailability(email);
        return exist;
    }

    public Boolean checkUsernameAvailability(String username){
        Boolean exist=userDao.checkUsernameAvailability(username);
        return exist;
    }

    public String validateRegistration(User user,String confirmPassword){
        if(user.getFirstname()=="" || user.getFirstname()==null){
            return "Firstname is required";
        }
        if(user.getLastname()=="" || user.getLastname()==null){
            return "Lastname is required";
        }
        if(user.getUsername()=="" || user.getUsername()==null){
            return "Username cannot be empty";
        }
        if(user.getEmail()=="" || user.getEmail()==null){
            return "Email address is required";
        }
        if(user.getPassword()=="" || user.getPassword()==null){
            return "Password cannot be empty";
        }
        if(!user.getPassword().equals(confirmPassword)){
            return "Password does not match";
        }
        return null;
    }


    public String login(String login_user,String login_password, HttpSession session){
        System.out.println("here");
        if(login_user=="" || login_user==null){
            return "Please enter username/password";
        }
        if(login_password=="" || login_password==null){
            return "Please enter password";
        }
        User user=userDao.findByUsernamePassword(login_user,login_password);
        if(user!=null){
            if(user.getActive()) {
                session.setAttribute("username", user.getUsername());
                session.setAttribute("firstname", user.getFirstname());
                session.setAttribute("lastname", user.getLastname());
                if(user.getAdmin()){
                    session.setAttribute("admin","yes");
                }
                else{
                    session.setAttribute("admin","no");
                }
                return null;
            }
            else{
                return "Account Not Active";
            }
        }
        else {
            System.out.println("invalid login");
            return "Incorrect username or password";
        }
    }

    public String register(String firstname,String lastname,String email,String username,
                           String password,String confirmPassword, CommonsMultipartFile photo,HttpSession session) throws IOException {

        System.out.println("in register method");
        User user=new User(firstname,lastname,email,username,password);
        String error=validateRegistration(user,confirmPassword);
        if(error!=null){
            return error;
        }
        else{
            if(!photo.isEmpty()){
                byte[] bytes = photo.getBytes();
                user.setPhoto(bytes);
            }
            try{
                userDao.save(user);
            }
            catch(Exception e){
                System.out.println(e);
                return "Some Error Occurred Please Try Again";
            }
            return null;
        }
    }

    public String edituserinfo(String firstname, String lastname, String username, CommonsMultipartFile photo,HttpSession session) {
        User user=userDao.findByUsername((String) session.getAttribute("username"));
        if(user.getFirstname()!=firstname)
            user.setFirstname(firstname);
        if(user.getLastname()!=lastname)
            user.setLastname(lastname);
        if(user.getUsername()!=username)
            user.setUsername(username);
        if(checkUsernameAvailability(username)) {
            return "Username not available";
        }
        if(!photo.isEmpty()) {
            byte[] bytes = photo.getBytes();
            user.setPhoto(bytes);
        }
        userDao.update(user);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("firstname",user.getFirstname());
        session.setAttribute("lastname",user.getLastname());
        return null;
    }
    //Getter and Setter


    public String changepassword(String password, String confirmPassword,HttpSession session) {
        if(password.equals(confirmPassword)){
            User user=userDao.findByUsername((String) session.getAttribute("username"));
            user.setPassword(password);
            userDao.update(user);
            return null;
        }
        else{
            return "password does not match";
        }
    }

    public ModelAndView userprofile(String userId){
        User user=userDao.findById(Integer.parseInt(userId));
        UserDto userdto=userDao.getUserDetails(Integer.parseInt(userId));
        List<Resource> userPosts=resourceDao.ResourcesofUser(user);
        //List<TopicDto> userTopics=topicDao.topicsOfUser(user);
        ModelAndView mv=new ModelAndView ("profile");
        mv.addObject("user",userdto);
        mv.addObject("userPost",userPosts);
        //mv.addObject("userTopic",userTopics);
        return mv;
    }

    public String getParticularUserCount(String type) {
        String result=String.valueOf(userDao.getParticularUserCount(type));
        System.out.println(result);
        return result;
    }

    public List<User> getLimitedUser(String offset, String limit,String type) {
            return userDao.findLimited(Integer.parseInt(offset),Integer.parseInt(limit),type);
    }
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


    public ModelAndView activateUser(String userid, HttpSession session) {
        User currentLogedInUser=userDao.findByUsername((String) session.getAttribute("username"));
        if(currentLogedInUser.getAdmin()){
            User userToActivate=userDao.findById(Integer.parseInt(userid));
            userToActivate.setActive(true);
            userDao.update(userToActivate);
            return new ModelAndView("redirect:/users");
        }
        else{
            return new ModelAndView("forward:/users","error","Not Authenticated");
        }
    }

    public ModelAndView deactivateUser(String userid, HttpSession session) {
        User currentLogedInUser=userDao.findByUsername((String) session.getAttribute("username"));
        if(currentLogedInUser.getAdmin()){
            User userToDeactivate=userDao.findById(Integer.parseInt(userid));
            userToDeactivate.setActive(false);
            userDao.update(userToDeactivate);
            return new ModelAndView("redirect:/users");
        }
        return new ModelAndView("forward:/users","error","Not Authenticated");
    }

    public String getTableFormat(List<User> users){
        String str="";
        for(User u:users){
            str+="<tr><td>"+u.getId()+"</td><td>"+u.getUsername()+"</td><td>"+u.getEmail()+"</td><td>"+u.getFirstname()+"</td>";
            str+="<td>"+u.getLastname()+"</td><td>";
            if(u.getActive())
                str+="yes</td><td><a href='/deactivateUser/"+u.getId()+"'>Deactivate</a></td>";
            else
                str+="no</td><td><a href='/activateUser/"+u.getId()+"'>Activate</td>";
        }
        return str;
    }

    public ModelAndView editprofile(HttpSession session) {
        User user=userDao.findByUsername((String) session.getAttribute("username"));
        UserDto userDto=userDao.getUserDetails(user.getId());
        List<TopicDto> topicList=topicDao.topicsOfUser(user);
        ModelAndView mv=new ModelAndView("EditProfile");
        mv.addObject("user",userDto);
        mv.addObject("topicList",topicList);
        return mv;
    }
}
