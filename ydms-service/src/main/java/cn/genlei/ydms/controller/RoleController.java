package cn.genlei.ydms.controller;

import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.RoleVO;
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

    @PostMapping
    public BaseVO add(@RequestBody RoleDTO roleDTO){
        return roleService.add(roleDTO);
    }

    @PutMapping("/{id:\\d+}")
    public BaseVO edit(@RequestBody RoleDTO roleDTO){
        return roleService.edit(roleDTO);
    }

    @DeleteMapping("/{id:\\d+}")
    public BaseVO delete(@PathVariable int id){
        return roleService.delete(id);
    }


    @GetMapping("/{id:\\d+}")
    public BaseVO detail(@PathVariable int id){
         RoleVO roleVO = roleService.detail(id);
         return ReturnUtil.success(roleVO);
    }
    @GetMapping
    public BaseVO list(){
        RoleListDTO dto = new RoleListDTO();
        dto.setPage(1);
        dto.setSize(1000);
        return roleService.list(dto);
    }

}
