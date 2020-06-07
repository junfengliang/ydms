package cn.genlei.ydms.controller;

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
public class AllUserController {
    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    UserService userService;

    @PostMapping("login")
    public BaseVO login(@RequestBody LoginDTO loginDTO){
        return userService.login(loginDTO);
    }

    @PostMapping("checkUsername")
    public BaseVO checkUsername(@RequestBody CheckUserDTO checkUserDTO){
        SleepUtil.sleep();
        return userService.checkUsername(checkUserDTO);
    }
    @PostMapping("sendVerifyCode")
    public BaseVO sendVerifyCode(@RequestBody CheckUserDTO checkUserDTO){
        SleepUtil.sleep();
        return userService.sendVerifyCode(checkUserDTO);
    }
    @PostMapping("resetPassword")
    public BaseVO resetPassword(@RequestBody ResetDTO resetDTO){
        SleepUtil.sleep();
        return userService.resetPassword(resetDTO);
    }
}
