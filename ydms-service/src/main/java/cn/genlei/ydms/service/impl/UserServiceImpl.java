package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.annotation.YdmsLog;
import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.entity.UserRole;
import cn.genlei.ydms.global.Constant;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.global.UserContextHolder;
import cn.genlei.ydms.repository.UserRepository;
import cn.genlei.ydms.repository.UserRoleRepository;
import cn.genlei.ydms.service.EmailService;
import cn.genlei.ydms.service.TokenService;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

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
    UserRoleRepository userRoleRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    EmailService emailService;

    @Autowired
    CacheManager cacheManager;

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
    @YdmsLog
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
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        saveRoleIds(user.getId(),userDTO.getRoleIds());
        log.debug(user.toString());
        return ReturnUtil.success();
    }

    private void saveRoleIds(Integer userId, Integer[] roleIds) {
        if(roleIds==null){
            if(log.isDebugEnabled()){
                log.debug("roleIds is null");
            }
            return;
        }
        List<UserRole> list = userRoleRepository.findAllByUserId(userId);
        List<UserRole> tobeDelete = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        if(list!=null){
            for(UserRole userRole:list){
                if(Arrays.binarySearch(roleIds,userRole.getRoleId())<0){
                    tobeDelete.add(userRole);
                }
                set.add(userRole.getRoleId());
            }
        }
        List<UserRole> tobeAdd = new ArrayList<>();

        for(Integer roleId : roleIds){
            if(!set.contains(roleId)){
                tobeAdd.add(generateUserRole(roleId,userId));
            }
        }
        if(tobeDelete.size()>0){
            userRoleRepository.deleteAll(tobeDelete);
        }
        if(tobeAdd.size()>0){
            userRoleRepository.saveAll(tobeAdd);
        }
        if(log.isDebugEnabled()){
            log.debug("save userRole , delete[{}],add[{}]"
                    ,tobeDelete.size(),tobeAdd.size());
        }
    }

    private UserRole generateUserRole(Integer roleId, Integer userId) {
        UserRole userRole = new UserRole();
        userRole.setCreateTime(new Date());
        userRole.setUpdateTime(new Date());
        userRole.setRoleId(roleId);
        userRole.setUserId(userId);
        return userRole;
    }

    @Override
    public BaseVO edit(UserDTO userDTO) {
        Optional<User> optionalUser = userRepository.findById(userDTO.getId());
        if(!optionalUser.isPresent()){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,"User not found!");
        }
        User user = optionalUser.get();
        user.setEmail(userDTO.getEmail());
        userRepository.save(user);
        saveRoleIds(userDTO.getId(),userDTO.getRoleIds());
        return ReturnUtil.success();
    }

    @Override
    public BaseVO delete(int id) {
        userRoleRepository.deleteByUserId(id);
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

    @Override
    public BaseVO detail(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(!optionalUser.isPresent()){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,"User not found!");
        }
        User user = optionalUser.get();
        UserDetailVO vo = new UserDetailVO();
        vo.setUsername(user.getUsername());
        List<UserRole> list = userRoleRepository.findAllByUserId(id);
        int[] roleIds = list.stream().mapToInt(item -> item.getRoleId()).toArray();
        vo.setRoleIds(roleIds);
        vo.setId(id);
        return ReturnUtil.success(vo);
    }

    @Override
    public BaseVO checkUsername(CheckUserDTO checkUserDTO) {
        Optional<User> optionalUser = userRepository.findByEmail(checkUserDTO.getUsername());
        if(optionalUser.isPresent()){
            return ReturnUtil.success();
        }else{
            return ReturnUtil.error(StatusCode.USER_NOT_EXIST,"Email not exist.");
        }
    }

    @Override
    public BaseVO sendVerifyCode(CheckUserDTO checkUserDTO) {
        String code = randomCode();
        String key = genCodeKey(checkUserDTO.getUsername());
        cacheManager.getCache(Constant.CACHE_YDMS).put(key,code);
        emailService.sendCode(checkUserDTO.getUsername(),code);
        return ReturnUtil.success();
    }

    private String genCodeKey(String username) {
        return String.format("code_%s",username);
    }

    private String randomCode() {
        return  String.valueOf(RandomUtils.nextLong(100_000,999_999));
    }

    @Override
    public BaseVO resetPassword(ResetDTO resetDTO) {
        String key = genCodeKey(resetDTO.getEmail());
        String code = cacheManager.getCache(Constant.CACHE_YDMS).get(key,String.class);
        if(!resetDTO.getCode().equals(code)){
            log.warn("input code [{}],cache code[{}]",resetDTO.getCode(),code);
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,"验证码错误！");
        }
        Optional<User> optionalUser = userRepository.findByEmail(resetDTO.getEmail());
        if(!optionalUser.isPresent()){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,"未找到用户！");
        }
        User user = optionalUser.get();
        user.setSalt(UUID.randomUUID().toString());
        String pass = DigestUtils.sha1Hex(resetDTO.getPassword() + user.getSalt());
        user.setPassword(pass);
        user.setUpdateTime(new Date());
        userRepository.save(user);
        return ReturnUtil.success();
    }
}
