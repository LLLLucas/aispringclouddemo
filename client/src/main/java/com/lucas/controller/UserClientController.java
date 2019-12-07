package com.lucas.controller;

import com.lucas.entity.User;
import com.lucas.entity.UserVO;
import com.lucas.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserClientController {
    @Autowired
    UserFeign userFeign;
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }
    @GetMapping("/findAll")
    @ResponseBody
    public UserVO findAll(@RequestParam("page")int page, @RequestParam("limit")int limit){
        int index=(page-1)*limit;
        List<User> all = userFeign.findAll(index, limit);
        UserVO userVO=new UserVO();
        userVO.setData(all);
        userVO.setCount(userFeign.count());
        return userVO;
    }
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id")long id){
         userFeign.deleteById(id);
         return "redirect:/user/redirect/user_manage";
    }

    @PostMapping("/save")
    public String  save( User user){
        userFeign.save(user);
        return "redirect:/user/redirect/user_manage";
    }

    @GetMapping("/count")
    public int count(){
        return userFeign.count();
    }





}
