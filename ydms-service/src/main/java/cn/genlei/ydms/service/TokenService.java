package cn.genlei.ydms.service;

import cn.genlei.ydms.entity.User;

/**
 * @author nid
 */
public interface TokenService {

    /**
     * assign a new token for user
     * @param user
     * @return
     */
    String assignToken(User user);

    User getUserInfo(String token);
}
