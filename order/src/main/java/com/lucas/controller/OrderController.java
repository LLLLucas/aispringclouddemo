package com.lucas.controller;

import com.lucas.entity.Order;
import com.lucas.entity.OrderVO;
import com.lucas.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderRepository orderRepository;
    @Value(("${server.port}"))
    private String port;
    @GetMapping("/index")
    public String index(){
        return "order的端口"+this.port;
    }
    @PostMapping("/save")
    public void save(@RequestBody  Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }
    @GetMapping("/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAll(@PathVariable("index")int index, @PathVariable("limit")int limit,@PathVariable("uid")int uid){
        List<Order> all = orderRepository.findAllByUId(index, limit,uid);
        OrderVO orderVO=new OrderVO();
        orderVO.setData(all);
        int count = orderRepository.countByUid(uid);
        orderVO.setCount(count);
        return orderVO;
    }
    @GetMapping("/findAllByState/{index}/{limit}")
    public OrderVO findAllByState(@PathVariable("index")int index, @PathVariable("limit")int limit){
        List<Order> allByState = orderRepository.findAllByState(index, limit);
        OrderVO orderVO=new OrderVO();
        int i = orderRepository.countByState();
        orderVO.setCount(i);
        orderVO.setData(allByState);
        return orderVO;
    }
    @PutMapping("/updateState/{id}")
    public void updateState(@PathVariable("id")long id){
        orderRepository.updateState(id);
    }

}
