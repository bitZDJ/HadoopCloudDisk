package com.example.controller;
import java.lang.String;
import java.util.List;

import com.example.entity.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.jws.WebParam;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes(value = {"name"})
public class UserController {

    @Autowired
    private UserService userService;

    //用户登陆，get
    @RequestMapping(value="/signin",method = RequestMethod.GET)
    public String signin(){
        System.out.print("到sign up,-get方式这一层");
        return "signin";
    }

    //返回创建用户界面，get
    @RequestMapping(value="/signup",method = RequestMethod.GET)
    public String signup(){
        return "signup";
    }

    //创建用户。post
    @RequestMapping(value = "/signup" ,method = RequestMethod.POST)
    public String signup(
            @RequestParam("fullname") String fullname,
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            @RequestParam("phone") String phone
    ){

        User user=new User(username,password,fullname,phone);
        System.out.println(user.getFullname()+user.getPassword()+"  ---这在userController里面");
        String res=userService.addUser(user);
        System.out.println("结果"+res+"  ---这在userController里面");
        if(res.equals("success"))
            return "signin";
        else return "signup";
    }

    //登陆检查,管理员还是用户
    @RequestMapping(value="/signin",method = RequestMethod.POST)
    public String signin(
            @RequestParam("username") String username,
            @RequestParam("password") String password, ModelMap model, HttpSession session){

        if(username.equals("") || password.equals("")){
            model.addAttribute("error","请输入用户名和密码！");
            return "signin";
        }
        if(username.equals("admin"))
        {
            if(password.equals("admin"))
                return "admin_index";
            else {
                model.addAttribute("error","用户名或密码错误！");
                return "signin";
            }
        }
        if(userService.signinCheck(username,password).equals("success")) {
            model.addAttribute("name", username);
            System.out.print(session.getAttribute("name"));
            return "user_index";
        }
        else {
            System.out.println(userService.signinCheck(username,password));
            model.addAttribute("error","用户名或密码错误！");
            return "signin";
        }

    }

    //用户返回主页
    @RequestMapping(value = "/user_index",method = RequestMethod.GET)
    public String sing(){
        return "user_index";
    }

    //用户查看详情
    @RequestMapping(value = "user_person",method = RequestMethod.GET)
    public String user_person(HttpSession session,Model model){

        User user=userService.selectUser((String) session.getAttribute("name"));
        model.addAttribute("user",user);
        return "user_person";
    }

    //注销登陆
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(SessionStatus sessionStatus){
        System.out.println("logout in UserController");
        sessionStatus.setComplete();
        return "signin";
    }

    //用户信息修改界面访问
    @RequestMapping(value = "user_update" ,method = RequestMethod.GET)
    public String user_update(HttpSession session,Model model){
        User user=userService.selectUser((String) session.getAttribute("name"));
        model.addAttribute("user",user);
        return "user_update";
    }

    //保存修改的信息，跳到个人信息页面
    @RequestMapping(value = "user_update" ,method = RequestMethod.POST)
    public String user_update(@RequestParam("fullname") String fullname,
                              @RequestParam("password") String password,
                              @RequestParam("phone") String phone, HttpSession session,Model model,SessionStatus sessionStatus) {
        User user1 = userService.selectUser((String) session.getAttribute("name"));
        String username=(String) session.getAttribute("name");
        String res = userService.deleteUser((String) session.getAttribute("name"));
        if (res.equals("success")) {
            User user = new User(username, password, fullname, phone);
            String res2 = userService.addUser(user);
            if (res2.equals("success")) {
                model.addAttribute("user", user);
                return "user_person";
            } else
            //删除成功，添加失败,则把原信息重新加入数据库
            {
                String res3 = userService.addUser(user1);
            }
        }
        //删除失败则显示用户的原来信息
        model.addAttribute("user", user1);
        return "user_person";
    }
}
