package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.repository.UserRepository;
import cn.genlei.ydms.service.TokenService;
import cn.genlei.ydms.utils.JwtUtil;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author nid
 */
@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @Override
    public String assignToken(User user) {
        return jwtUtil.generate(String.valueOf(user.getId()));
    }

    @Override
    public User getUserInfo(String token) {
        String userId = jwtUtil.getSubject(token);
        int uid = NumberUtils.toInt(userId);
        Optional<User> optionalUser = userRepository.findById(uid);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        return null;
    }

}
