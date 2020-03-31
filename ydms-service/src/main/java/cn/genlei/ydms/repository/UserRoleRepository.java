package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nid
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {


}
