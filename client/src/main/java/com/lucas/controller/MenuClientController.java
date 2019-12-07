package com.lucas.controller;

import com.lucas.entity.Menu;
import com.lucas.entity.MenuVO;
import com.lucas.entity.Type;
import com.lucas.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuClientController {
    @Autowired
    MenuFeign menuFeign;
    @GetMapping("/findAll")
    @ResponseBody
    public MenuVO finAll(@RequestParam("page")int page, @RequestParam("limit")int limit){
        int index=(page-1)*limit;
        return menuFeign.findAll(index,limit);
    }
    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location") String location){
        return location;
    }
    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id")long id){
        menuFeign.deleteById(id);
        return "redirect:/menu/redirect/menu_manage";

    }
    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        List<Type> allTypes = menuFeign.findAllTypes();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("menu_add");
        modelAndView.addObject("list",allTypes);
        return modelAndView;

    }

    @PostMapping("/save")
    public String save(Menu menu){
        menuFeign.save(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @PostMapping("/update")
    public String update(Menu menu){
        menuFeign.update(menu);
        return "redirect:/menu/redirect/menu_manage";
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        Menu byId = menuFeign.findById(id);
        List<Type> allTypes = menuFeign.findAllTypes();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu",byId);
        modelAndView.addObject("list",allTypes);
        return modelAndView;
    }


}
