package com.example.dao;

import com.example.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import java.util.List;
@Repository
public interface UserDao {


    /**
     * 根据用户名统计是否有该用户
     * @param username
     */
    public  int count(String username);

    /**
     * 登陆验证用户名和密码
     * @param user
     */
    public  int check(User user);

    /**
     * 用户自己查询得到其信息
     * @param username
     */
    public User selectUser(String username);

    /**
     * 添加用户
     * @param user
     */
    public void insertUser(User user);

    /**
     * 查询所有用户列表
     * @param
     */
    public List<User> getUserList();

    /**
     * 根据姓名查用户
     * @param username
     */
    public List<User> selectUsername(String username);


    /**
     * 删除用户
     * @param username
     */
    public void deleteUser(String username);

    public User selectByFullname(String fullname);

}
