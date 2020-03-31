package cn.genlei.ydms.controller;

import cn.genlei.ydms.dto.MenuDTO;
import cn.genlei.ydms.dto.RoleDTO;
import cn.genlei.ydms.dto.RoleListDTO;
import cn.genlei.ydms.entity.Menu;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.service.MenuService;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: Junfeng
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    MenuService menuService;

    @PostMapping("add")
    public BaseVO add(@RequestBody MenuDTO menuDTO){
        return menuService.add(menuDTO);
    }

    @PostMapping("edit")
    public BaseVO edit(@RequestBody MenuDTO menuDTO){
        return menuService.edit(menuDTO);
    }

    @PostMapping("delete")
    public BaseVO delete(@RequestParam int id){
        return menuService.delete(id);
    }


    @GetMapping("listAll")
    public BaseVO listAll(){
        return menuService.listAll();
    }
    @GetMapping("listLeft")
    public BaseVO listLeft(){
        return menuService.listLeft();
    }
}
