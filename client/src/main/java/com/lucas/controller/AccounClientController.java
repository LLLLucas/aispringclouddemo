package com.lucas.controller;

import com.lucas.entity.Admin;
import com.lucas.entity.User;
import com.lucas.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccounClientController {
    @Autowired
    AccountFeign accountFeign;
    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("type") String type, HttpSession session){
        Object login = accountFeign.login(username, password, type);
        LinkedHashMap<String,Object> hashMap=(LinkedHashMap<String, Object>)login;
        System.out.println(login);
        String result=null;
        if(login==null){
            result="login";

        }else{
            switch (type){
                case "user":{
                    User user=new User();
                    user.setId(Long.parseLong(String.valueOf(hashMap.get("id"))));
                    user.setUsername((String)hashMap.get("username"));
                    user.setPassword((String)hashMap.get("password"));
                    user.setNickname((String)hashMap.get("nickname"));



                    session.setAttribute("user",user);
                    result="index";
                    break;
                }
                case "admin":{
                    Admin admin=new Admin();
                    admin.setId(Long.parseLong(String.valueOf(hashMap.get("id"))));
                    admin.setUsername((String)hashMap.get("username"));
                    session.setAttribute("admin",admin);
                    result="main";
                    break;
                }
            }
        }

        return result;
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();;
        return "redirect:/login.html";
    }



}
