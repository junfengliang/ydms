package cn.genlei.ydms.service;

import cn.genlei.ydms.dto.*;
import cn.genlei.ydms.vo.BaseVO;

/**
 * The interface User service.
 * @author nid
 */
public interface RoleService {

    /**
     * @param roleListDTO
     * @return
     */
    BaseVO list(RoleListDTO roleListDTO);

    /**
     * @param roleDTO
     * @return
     */
    BaseVO add(RoleDTO roleDTO);

    /**
     * @param roleDTO
     * @return
     */
    BaseVO edit(RoleDTO roleDTO);

    /**
     * @param id
     * @return
     */
    BaseVO delete(int id);

}
