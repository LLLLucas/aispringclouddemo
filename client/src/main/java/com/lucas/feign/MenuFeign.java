package com.lucas.feign;

import com.lucas.entity.Menu;
import com.lucas.entity.MenuVO;
import com.lucas.entity.Type;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "menu")
public interface MenuFeign {
    @GetMapping("/menu/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index")int index, @PathVariable("limit")int limit);

    @DeleteMapping("/menu/deleteById/{id}")
    public void deleteById(@PathVariable("id")long id);

    @GetMapping("/menu/findAllTypes")
    public List<Type> findAllTypes();

    @PostMapping("/menu/save")
    public void save(Menu menu);

    @PutMapping("/menu/update")
    public void update(Menu menu);

    @GetMapping("/menu/findById/{id}")
    public Menu findById(@PathVariable("id") long id);
}
