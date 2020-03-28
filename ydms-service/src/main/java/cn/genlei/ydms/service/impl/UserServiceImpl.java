package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.LoginDTO;
import cn.genlei.ydms.dto.UserDTO;
import cn.genlei.ydms.dto.UserListDTO;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.global.UserContextHolder;
import cn.genlei.ydms.repository.UserRepository;
import cn.genlei.ydms.service.TokenService;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.LoginVO;
import cn.genlei.ydms.vo.UserInfoVO;
import cn.genlei.ydms.vo.UserListVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author nid
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    LocaleMessage localeMessage;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public BaseVO login(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername());
        if(user==null){
            return userOrPassError();
        }
        if(!passMatch(user,loginDTO.getPassword())){
            return userOrPassError();
        }
        LoginVO loginVO = new LoginVO();
        String token = tokenService.assignToken(user);
        loginVO.setToken(token);
        return ReturnUtil.success(loginVO);
    }

    @Override
    public BaseVO list(UserListDTO userListDTO) {
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(userListDTO.getPage()-1,userListDTO.getSize(),sort);
        Page<User> page = userRepository.findAll(pageable);
        UserListVO vo = new UserListVO();
        vo.setPage(ReturnUtil.page(page));
        vo.setList(page.getContent());
        return ReturnUtil.success(vo);
    }

    @Override
    public BaseVO add(UserDTO userDTO) {
        User user = new User();
        user.setCreatetime(new Date());
        user.setUpdatetime(new Date());
        user.setUsername(userDTO.getUsername());
        userRepository.save(user);
        log.debug(user.toString());
        return ReturnUtil.success();
    }

    @Override
    public BaseVO edit(UserDTO userDTO) {
        return null;
    }

    @Override
    public BaseVO delete(int id) {
        userRepository.deleteById(id);
        return ReturnUtil.success();
    }

    private boolean passMatch(User user, String password) {
        String calc = DigestUtils.sha1Hex(password + user.getSalt());
        if(calc.equals(user.getPassword())){
            return true;
        }
        log.debug("calc pass:{}, db pass:{}, input pass:{}, salt:{}",
                calc,user.getPassword(),password,user.getSalt());
        return false;
    }

    private BaseVO userOrPassError() {
        String msg = localeMessage.getMessage("username.or.password.error");
        return ReturnUtil.error(StatusCode.USERNAME_OR_PASSWORD_ERROR,msg);
    }

    @Override
    public BaseVO logout() {
        return ReturnUtil.success();
    }

    @Override
    public BaseVO info() {
        //TODO role
        User user = UserContextHolder.getCurrent();
        UserInfoVO vo = new UserInfoVO();
        vo.setAvatar("https://wpimg.wallstcn.com/e7d23d71-cf19-4b90-a1cc-f56af8c0903d.png");
        vo.setIntroduction("intro");
        vo.setName(user.getUsername());
        vo.setRoles(new String[]{"admin"});
        return ReturnUtil.success(vo);
    }
}
