package com.lucas.feign;

import com.lucas.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("user")
public interface UserFeign {
    @GetMapping("/user/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index")int index,@PathVariable("limit")int limit);
    @GetMapping("/user/count")
    public int count();
    @PostMapping("/user/save")
    public void save(User user);
    @PutMapping("/user/update")
    public void update(User user);
    @DeleteMapping("/user/deleteById/{id}")
    public void deleteById(@PathVariable("id")long id);
    @GetMapping("/user/findById/{id}")
    public User findById(@PathVariable("id") long id);

}
