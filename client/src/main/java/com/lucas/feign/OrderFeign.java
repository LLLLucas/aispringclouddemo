package com.lucas.feign;

import com.lucas.entity.Order;
import com.lucas.entity.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("order")
public interface OrderFeign {
    @PostMapping("/order/save")
    public Object save( Order order);
    @GetMapping("/order/findAllByUid/{index}/{limit}/{uid}")
    public OrderVO findAllByUid(@PathVariable("index")int index,@PathVariable("limit")int limit,@PathVariable("uid")long uid);

    @GetMapping("/order/findAllByState/{index}/{limit}")
    public OrderVO findAllByState(@PathVariable("index")int index,@PathVariable("limit")int limit);

    @PutMapping("/order/updateState/{id}")
    public void updateState(@PathVariable("id")long id);
}
