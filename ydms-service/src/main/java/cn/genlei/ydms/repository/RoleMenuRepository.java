package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface Role menu repository.
 *
 * @author nid
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {

    /**
     * Find all by role id list.
     *
     * @param roleId the role id
     * @return the list
     */
    List<RoleMenu> findAllByRoleId(int roleId);

    /**
     * Delete by role id int.
     *
     * @param roleId the role id
     * @return the int
     */
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("delete from RoleMenu r where r.roleId=:roleId")
    int deleteByRoleId(int roleId);
}
