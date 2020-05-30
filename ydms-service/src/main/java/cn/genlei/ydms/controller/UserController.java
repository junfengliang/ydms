package cn.genlei.ydms.controller;

import cn.genlei.ydms.annotation.YdmsAuth;
import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.global.LocaleMessage;

import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.utils.SleepUtil;
import cn.genlei.ydms.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * @author nid
 */
@RestController
@RequestMapping("user")
@YdmsAuth
public class UserController {
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    UserService userService;


    @PostMapping
    public BaseVO add(@RequestBody UserDTO userDTO){
        return userService.add(userDTO);
    }

    @PutMapping("/{id:\\d+}")
    public BaseVO edit(@RequestBody UserDTO userDTO){
        return userService.edit(userDTO);
    }

    @GetMapping("list")
    public BaseVO list(@RequestParam int page,
                       @RequestParam("limit") int size){
        UserListDTO dto = new UserListDTO();
        dto.setPage(page);
        dto.setSize(size);
        return userService.list(dto);
    }

    @PostMapping("logout")
    public BaseVO logout(){
        return userService.logout();
    }

    @GetMapping("info")
    public BaseVO info(){
        return userService.info();
    }
    @GetMapping("/{id:\\d+}")
    public BaseVO detail(@PathVariable int id){
        return userService.detail(id);
    }
    @DeleteMapping("/{id:\\d+}")
    public BaseVO delete(@PathVariable int id){
        return userService.delete(id);
    }


}
