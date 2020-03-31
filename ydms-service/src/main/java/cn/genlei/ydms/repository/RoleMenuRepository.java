package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.RoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nid
 */
@Repository
public interface RoleMenuRepository extends JpaRepository<RoleMenu, Integer> {


}
