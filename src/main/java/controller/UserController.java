package controller;

import entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


@Controller
public class UserController extends ParentController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/login",method=RequestMethod.POST)
    ModelAndView login(@RequestParam String login_user, @RequestParam String login_password, HttpSession session){
        String result=userService.login(login_user,login_password,session);
        if(result==null){
            return new ModelAndView("redirect:/dashboard");
        }
        else{
            return new ModelAndView("forward:/","error",result);
        }
    }

    @RequestMapping(value="/register",method= RequestMethod.POST)
    ModelAndView register(@RequestParam String firstname, @RequestParam String lastname,
                          @RequestParam String email , @RequestParam String username,
                          @RequestParam String password, @RequestParam String confirmPassword,
                          @RequestParam CommonsMultipartFile photo,HttpSession session) {

        String result="";
        try {
            result = userService.register(firstname, lastname, email, username, password, confirmPassword, photo, session);
        }catch (Exception e){
            return new ModelAndView("index","error","Please Try again");
        }
        if(result==null){
            return new ModelAndView("index","success","Registered SuccessFully!");
        }
        else{
            return new ModelAndView("index","error",result);
        }
    }


    @RequestMapping(value="/logout")
    ModelAndView logout(HttpSession session){
        session.invalidate();
        return new ModelAndView("forward:/");
    }

    @ResponseBody
    @RequestMapping(value="/getUserCount")
    String getUserCountByType(@RequestParam String type){
        return userService.getParticularUserCount(type);
    }


    @RequestMapping(value="/edituserinfo")
    ModelAndView edituserinfo(@RequestParam String firstname,@RequestParam String lastname,@RequestParam String username,
                              @RequestParam CommonsMultipartFile photo,HttpSession session){
        String result=userService.edituserinfo(firstname,lastname,username,photo,session);
        if(result==null){
            return new ModelAndView("EditProfile","success","profile updated successfully");
        }
        else{
            return new ModelAndView("EditProfile","error",result);
        }
    }

    @RequestMapping(value = "/changepassword")
    ModelAndView changepassword(@RequestParam String password,@RequestParam String confirmPassword,HttpSession session){
        String result=userService.changepassword(password,confirmPassword,session);
        if(result==null){
            return new ModelAndView("EditProfile","success","password updated successfully");
        }
        else {
            return new ModelAndView("EditProfile", "error", result);
        }
    }

    @ResponseBody
    @RequestMapping(value="/checkUsernameAvailability/{username}")
    String checkUsernameAvailability(@PathVariable("username") String username,Model model){
        System.out.println("here : "+username);
        Boolean exist=userService.checkUsernameAvailability(username);
        if(exist==false) {
            return "available";
        }
        else {
            return "taken";
        }
    }

    @ResponseBody
    @RequestMapping(value="/checkEmailAvailability")
    String checkEmailAvailability(@RequestParam("email") String email,Model model){
        System.out.println("here : "+email);
        Boolean exist=userService.checkEmailAvailability(email);
        if(exist==false) {
            System.out.println("available");
            return "available";
        }
        else {
            System.out.println("taken");
            return "taken";
        }
    }

    @RequestMapping(value="/profile/{id}")
    ModelAndView userprofile(@PathVariable String id){
       return userService.userprofile(id);
    }

    @RequestMapping(value="/getLimitedUser/")
    @ResponseBody
    void getLimitedUser(@RequestParam String offset,@RequestParam String limit,@RequestParam String type,
                        HttpServletResponse response) throws IOException {
        List<User> users=userService.getLimitedUser(offset,limit,type);
        String str=userService.getTableFormat(users);
        response.getWriter().print(str);
    }

    @RequestMapping(value="/activateUser/{userid}")
    ModelAndView activateUser(@PathVariable String userid, HttpSession session){
        return userService.activateUser(userid,session);

    }

    @RequestMapping(value="/deactivateUser/{userid}")
    ModelAndView deactivateUser(@PathVariable String userid,HttpSession session){
        return userService.deactivateUser(userid,session);
    }

    //TO prevent site from crashing if user accidently hit the url
    @RequestMapping(value={"/login","/register"},method= RequestMethod.GET)
    ModelAndView dummy(){
        return new ModelAndView("redirect:/dashboard");
    }



    //SETTER
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
