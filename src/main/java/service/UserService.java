package service;

import dao.UserDao;
import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

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
            System.out.println("logged In");
            session.setAttribute("username",user.getUsername());
            session.setAttribute("firstname",user.getFirstname());
            session.setAttribute("lastname",user.getLastname());
            return null;
        }
        else {
            System.out.println("invalid login");
            return "Incorrect username or password";
        }
    }

    public String register(String firstname,String lastname,String email,String username,
                           String password,String confirmPassword, CommonsMultipartFile photo,HttpSession session){

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
/*          System.out.println(user);
            FileOutputStream outputStream = new FileOutputStream("D:/photo.jpg");
            outputStream.write(bytes);
            outputStream.close();
            */
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

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }


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
}
