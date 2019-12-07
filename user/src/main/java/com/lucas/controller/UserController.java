package com.lucas.controller;

import com.lucas.entity.User;
import com.lucas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Value("${server.port}")
    private String port;
    @GetMapping("/index")
    public String index(){
        return port;
    }
    @GetMapping("/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index")int index,@PathVariable("limit")int limit){
        List<User> all = userRepository.findAll(index,limit);
        return all;
    }
    @GetMapping("/findById/{id}")
    public User findById(@PathVariable("id") long id){
        User byId = userRepository.findById(id);
        return byId;
    }
    @PutMapping("/update")
    public void update(@RequestBody User user){
        userRepository.update(user);
    }
    @PostMapping("/save")
    public void save(@RequestBody User user){
        user.setRegisterdate(new Date());
        userRepository.save(user);
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        userRepository.deleteById(id);
    }
    @GetMapping("/count")
    public int count(){
        return userRepository.count();
    }
}
