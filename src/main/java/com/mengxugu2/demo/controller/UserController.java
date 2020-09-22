package com.mengxugu2.demo.controller;


import com.mengxugu2.demo.entities.User;
import com.mengxugu2.demo.mapper.UserMapper;
import com.mengxugu2.demo.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: bii_management_system
 * @description:
 * @author: tgw
 * @create: 2020-08-28 15:28
 */
@Controller
public class UserController {
    @Autowired
    UserMapper userMapper;

    @GetMapping("/users")
    public String list(Map<String,Object> map,User user){
        List<User> users = userMapper.getUsers(user);
        map.put("users",users);
        //回显搜索框内输入的数据
        map.put("username",user.getUsername());

        return "user/list";
    }




    @GetMapping("/user/{id}")
    public String view(@PathVariable("id") Integer id,
                       @RequestParam(value = "type",defaultValue = "view") String type, Map<String,Object> map){

        User user = userMapper.getUserById(id);
        map.put("user",user);
        return "user/"+ type;
    }

    //提交修改供应商信息
    @PutMapping("/user")
    public String update(User user){

        userMapper.updateUser(user);
        return "redirect:/users";

    }

    //删除供应商信息
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable ("id") Integer id){
        userMapper.deleteUserById(id);

        return "redirect:/users";

    }
//
//    //跳转到 添加页面
    @GetMapping("/user")
    public String toAddPage(){

        return "user/add";

    }

    @PostMapping("/user")
    public String Add( User user){
        userMapper.addUser(user);
        return "redirect:/users";

    }


    @GetMapping("/user/pwd")
    public String toUpdatePwdPage(){

        return "main/password";

    }

    @ResponseBody
    @GetMapping("user/pwd/{oldPwd}")
    public  Boolean checkPwd(HttpSession session,@PathVariable("oldPwd") String oldPwd){
//1. 从Session中获取当前登录用户的User对 象
    User user = (User)session.getAttribute("loginUser");
    if (user.getPassword().equals(new Md5Hash(oldPwd,user.getSalt(),1024).toHex())){
        return  true ;  //旧密码输入正确
       }
        return  false;
    }


    @PostMapping("/user/pwd")
    public  String updatePwd(HttpSession session, String password){

//1.从Session中获取当前登录用户信息
        User user = (User)session.getAttribute("loginUser");
//2. 更新密码,把盐值散列加密后的新密码存储到数据库中
        String salt = SaltUtils.getSalt(8);
        Md5Hash md5Hash = new Md5Hash(password,salt,1024);
        user.setSalt(salt);
        user.setPassword(md5Hash.toHex());
//3.提交到数据库
        userMapper.updateUser(user);
//4.注销重新登录
       return  "redirect:/logout";
    }
}
