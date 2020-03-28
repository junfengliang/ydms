package cn.genlei.ydms.service;

import cn.genlei.ydms.dto.LoginDTO;
import cn.genlei.ydms.dto.UserDTO;
import cn.genlei.ydms.dto.UserListDTO;
import cn.genlei.ydms.vo.BaseVO;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * user login interface
     *
     * @param loginDTO the login dto
     * @return base vo
     */
    BaseVO login(LoginDTO loginDTO);

    /**
     * List user.
     *
     * @param userListDTO the user list dto
     * @return base vo
     */
    BaseVO list(UserListDTO userListDTO);

    /**
     * Add user.
     *
     * @param userDTO the user dto
     * @return the base vo
     */
    BaseVO add(UserDTO userDTO);

    /**
     * Edit user.
     *
     * @param userDTO the user dto
     * @return the base vo
     */
    BaseVO edit(UserDTO userDTO);

    /**
     * Delete user.
     *
     * @param id the id
     * @return the base vo
     */
    BaseVO delete(int id);

    /**
     * user logout interface
     *
     * @return base vo
     */
    BaseVO logout();

    /**
     * user info interface
     *
     * @return base vo
     */
    BaseVO info();

}
