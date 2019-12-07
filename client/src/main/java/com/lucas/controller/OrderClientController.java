package com.lucas.controller;

import com.lucas.entity.*;
import com.lucas.feign.OrderFeign;
import com.lucas.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderClientController {
    @Autowired
    OrderFeign orderFeign;
    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid")long mid, HttpSession session){
        User user = (User)session.getAttribute("user");
        Order order=new Order();
        order.setUser(user);
        Menu menu=new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        order.setDate(new Date());
        orderFeign.save(order);
        return "order";
    }
    @GetMapping("/findAllByUid")
    @ResponseBody
    public OrderVO findAllByUid(@RequestParam("page")int page,@RequestParam("limit")int limit,HttpSession session){
        int index=(page-1)*limit;
        User user =(User) session.getAttribute("user");
        OrderVO allByUid = orderFeign.findAllByUid(index, limit, user.getId());

        return allByUid;
    }
    @GetMapping("/findAllByState")
    @ResponseBody
    public OrderVO findAllByState(@RequestParam("page")int page,@RequestParam("limit")int limit){
        int index=(page-1)*limit;
        OrderVO allByState = orderFeign.findAllByState(index, limit);
        return allByState;

    }
    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id")long id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }

}
