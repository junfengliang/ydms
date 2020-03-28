package cn.genlei.ydms.controller;

import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nid
 */
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    RoleService roleService;

    @PostMapping("add")
    public BaseVO add(@RequestBody RoleDTO roleDTO){
        return roleService.add(roleDTO);
    }

    @PostMapping("edit")
    public BaseVO edit(@RequestBody RoleDTO roleDTO){
        return roleService.edit(roleDTO);
    }

    @PostMapping("delete")
    public BaseVO delete(@RequestParam int id){
        return roleService.delete(id);
    }


    @GetMapping("list")
    public BaseVO list(@RequestParam int page,
                       @RequestParam("limit") int size){
        RoleListDTO dto = new RoleListDTO();
        dto.setPage(page);
        dto.setSize(size);
        return roleService.list(dto);
    }

}
