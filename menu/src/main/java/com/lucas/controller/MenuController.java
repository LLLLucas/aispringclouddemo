package com.lucas.controller;

import com.lucas.entity.Menu;
import com.lucas.entity.MenuVO;
import com.lucas.entity.Type;
import com.lucas.repository.MenuRepository;
import com.lucas.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Value("${server.port}")
    private String port;
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private TypeRepository typeRepository;
    @GetMapping("/index")
    public String index(){
        return port;
    }
    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit){
        List<Menu> list=menuRepository.findAll(index,limit);
        int count = menuRepository.count();
        MenuVO menuVO=new MenuVO(0,"",count,list);
        return menuVO;
    }
    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id")long id){
        menuRepository.deleteById(id);
    }
    @GetMapping("/findAllTypes")
    public List<Type> findAll(){
        return typeRepository.findAll();
    }
    @PostMapping("/save")
    public void  save(@RequestBody Menu menu){
        menuRepository.save(menu);
    }
    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        Menu byId = menuRepository.findById(id);
        return byId;
    }
    @PutMapping("/update")
    public void update(@RequestBody Menu menu){
        long id = menu.getType().getId();
        menuRepository.update(menu);
    }



}
