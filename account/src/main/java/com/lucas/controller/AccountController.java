package com.lucas.controller;

import com.lucas.repository.AdminRepository;
import com.lucas.repository.UserRepository;
import com.netflix.ribbon.proxy.annotation.Http;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    UserRepository userRepository;
    @Value("${server.port}")
    private String port;
    @GetMapping("/index")
    public String index(){
        return port;
    }
    @GetMapping("/login/{username}/{password}/{type}")
    public Object login(@PathVariable("username") String username,@PathVariable("password") String password,@PathVariable("type") String type){
        Object object=null;
        switch (type) {
            case "user":
                object = userRepository.login(username, password);
                break;
            case "admin":
                object=adminRepository.login(username,password);
                break;
        }
        return object;
    }

}
