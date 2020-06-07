package cn.genlei.ydms.service;

import cn.genlei.ydms.dto.*;
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

    /**
     * User Detail.
     *
     * @param id the id
     * @return the base vo
     */
    BaseVO detail(int id);

    /**
     * Check username .
     *
     * @param checkUserDTO the check user dto
     * @return base vo
     */
    BaseVO checkUsername(CheckUserDTO checkUserDTO);

    /**
     * Send verify code base vo.
     *
     * @param checkUserDTO the check user dto
     * @return the base vo
     */
    BaseVO sendVerifyCode(CheckUserDTO checkUserDTO);

    /**
     * Reset password base vo.
     *
     * @param resetDTO the reset dto
     * @return the base vo
     */
    BaseVO resetPassword(ResetDTO resetDTO);

    BaseVO changePassword(ChangePasswordDTO passwordDTO);
}
