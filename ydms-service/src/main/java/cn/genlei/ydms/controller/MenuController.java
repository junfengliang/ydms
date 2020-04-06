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

import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Semaphore;

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

    @PostMapping
    public BaseVO add(@RequestBody MenuDTO menuDTO){
        return menuService.add(menuDTO);
    }

    @PutMapping("/{id:\\d+}")
    public BaseVO edit(@RequestBody MenuDTO menuDTO){
        return menuService.edit(menuDTO);
    }

    @DeleteMapping("/{id:\\d+}")
    public BaseVO delete(@PathVariable int id){
        return menuService.delete(id);
    }


    @GetMapping
    public BaseVO list(@RequestParam(defaultValue = "true") boolean all){
        if(all){
            return menuService.listAll();
        }else{
            return menuService.listLeft();
        }
    }
    @GetMapping("/demo")
    public String list(@RequestParam(defaultValue = "true") boolean all,
                       HttpServletResponse response){
        response.setStatus(401);
        response.setHeader("www-Authenticate","Basic realm= \"family\"");
        return "401";
    }

}
