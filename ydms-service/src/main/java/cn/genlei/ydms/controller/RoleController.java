package cn.genlei.ydms.controller;

import cn.genlei.ydms.annotation.YdmsAuth;
import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.entity.Role;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.RoleListVO;
import cn.genlei.ydms.vo.RoleVO;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author nid
 */
@Api(tags = {"Role interface"})
@RestController
@RequestMapping("role")
@YdmsAuth
public class RoleController {
    private final static String roleDesc = "Get role detail via role id, long long long long";
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    RoleService roleService;

    @ApiOperation(
            value = "添加角色",
            notes="Add role"
    )
    @PostMapping
    public BaseVO<Role> add(@RequestBody RoleDTO roleDTO){
        return roleService.add(roleDTO);
    }

    @PutMapping("/{id:\\d+}")
    public BaseVO<Role> edit(@RequestBody RoleDTO roleDTO){
        return roleService.edit(roleDTO);
    }

    @DeleteMapping("/{id:\\d+}")
    public BaseVO delete(@PathVariable int id){
        return roleService.delete(id);
    }

    @ApiOperation(
            value = "Get role detail",
            notes=roleDesc
    )
    @ApiImplicitParam(name = "id", value = "Role id", paramType = "path", required = true, dataType = "int")
    @GetMapping("/{id:\\d+}")
    public BaseVO<RoleVO> detail(@PathVariable int id){
         RoleVO roleVO = roleService.detail(id);
         return ReturnUtil.success(roleVO);
    }
    @GetMapping
    public BaseVO<RoleListVO> list(){
        RoleListDTO dto = new RoleListDTO();
        dto.setPage(1);
        dto.setSize(1000);
        return roleService.list(dto);
    }

}
