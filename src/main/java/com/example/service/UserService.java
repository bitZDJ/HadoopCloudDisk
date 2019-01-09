package com.example.service;
import com.example.dao.UserDao;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserService() {
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    /**
     ********业务层：*******
     * 检查用户名是否存在、
     * 用户得到其信息、
     * 增加用户
     * 查询所有用户
     * 查询用户,管理员可以模糊查询，在业务处理层添加了“%”
     * 删除用户
     */

    /**
     * 检查用户名是否存在
     * @param username
     */
    public String checkUser(String username){
        int result =userDao.count(username);
        if(result==0)
            return "success";
        else return "error";
    }

    /**
     * 登陆验证密码和用户名
     * @param username
     * @param password
     */
    public String signinCheck(String username, String password){
        System.out.println("signinCheck in UserService"+username);
        User user=new User(username,password,"","");
        int result =userDao.count(username);
        if(result==1)
        {
            int res=userDao.check(user);
            if(res==1)
            {
                return "success";
            }
            else return "password error";
        }
        else return "username error";
    }


    /**
     * 查询得到该用户信息
     * @param username
     */
    public User selectUser(String username){
        User user=userDao.selectUser(username);
        return user;
    }

    /**
     * 添加用户
     * @param user
     */
    public String addUser(User user){
        try{
            System.out.println(user.getFullname()+user.getPassword()+"  返回成功---这在userService里面");
            userDao.insertUser(user);
            return "success";
        }
        catch (Exception e){
            System.out.println(user.getFullname()+user.getPassword()+"  返回失败，估计语句错误---这在userService里面");
            return "error";
        }
    }

    /**
     * 查询所有用户列表
     * @param
     */
    public List<User> getUserList(){
        return userDao.getUserList();
    }

    /**
     * 根据姓名查用户
     * @param username
     */
    public List<User> selectUsername(String username){
        username="%"+username+"%";
        return userDao.selectUsername(username);
    }

    /**
     * 删除用户
     * @param username
     */
    public String deleteUser(String username){
        try{
            userDao.deleteUser(username);
            System.out.println("delete User success in UserDao");
            return "success";
        }catch (Exception e){
            System.out.println("delete User fail in UserDao");
            return "error";
        }
    }


    public User selectByFullname(String fullname){
        return userDao.selectByFullname(fullname);
    }
}
