package cn.genlei.ydms.service;

import cn.genlei.ydms.dto.MenuDTO;
import cn.genlei.ydms.vo.BaseVO;

/**
 * The interface User service.
 *
 * @author nid
 */
public interface MenuService {

    /**
     * List left menu.
     *
     * @return the base vo
     */
    BaseVO listLeft();

    /**
     * List all menu.
     *
     * @return the base vo
     */
    BaseVO listAll();

    /**
     * Add menu.
     *
     * @param menuDTO the menu dto
     * @return the base vo
     */
    BaseVO add(MenuDTO menuDTO);

    /**
     * Edit menu.
     *
     * @param menuDTO the role dto
     * @return the base vo
     */
    BaseVO edit(MenuDTO menuDTO);


    /**
     * Delete menu.
     *
     * @param id the id
     * @return the base vo
     */
    BaseVO delete(int id);

}
