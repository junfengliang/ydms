package cn.genlei.ydms.controller;

import cn.genlei.ydms.dto.LoginDTO;
import cn.genlei.ydms.dto.UserDTO;
import cn.genlei.ydms.dto.UserListDTO;
import cn.genlei.ydms.global.LocaleMessage;

import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.vo.BaseVO;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

/**
 * @author nid
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    UserService userService;

    @PostMapping("login")
    public BaseVO login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }
    @PostMapping("add")
    public BaseVO add(@RequestBody UserDTO userDTO){
        return userService.add(userDTO);
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

    @GetMapping("demo")
    public String demo(){
        return "demo " + System.currentTimeMillis();
    }



}
