package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nid
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * find user by username
     * @param userName
     * @return
     */
    User findByUsername(String userName);

}
