package cn.genlei.ydms.service;

import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.entity.Role;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.RoleListVO;
import cn.genlei.ydms.vo.RoleVO;

/**
 * The interface User service.
 *
 * @author nid
 */
public interface RoleService {

    /**
     * List base vo.
     *
     * @param roleListDTO the role list dto
     * @return base vo
     */
    BaseVO<RoleListVO> list(RoleListDTO roleListDTO);

    /**
     * Add base vo.
     *
     * @param roleDTO the role dto
     * @return base vo
     */
    BaseVO<Role> add(RoleDTO roleDTO);

    /**
     * Edit base vo.
     *
     * @param roleDTO the role dto
     * @return base vo
     */
    BaseVO<Role> edit(RoleDTO roleDTO);

    /**
     * Delete base vo.
     *
     * @param id the id
     * @return base vo
     */
    BaseVO delete(int id);

    /**
     * Detail role vo.
     *
     * @param id the id
     * @return the role vo
     */
    RoleVO detail(int id);
}
