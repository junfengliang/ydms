package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nid
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {


}
