package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The interface User role repository.
 *
 * @author nid
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    /**
     * Find all by user id list.
     *
     * @param userId the user id
     * @return the list
     */
    List<UserRole> findAllByUserId(int userId);

    /**
     * Delete by user id int.
     *
     * @param userId the user id
     * @return the int
     */
    @Transactional(rollbackOn = Exception.class)
    @Modifying
    @Query("delete from UserRole u where u.userId=:userId")
    int deleteByUserId(int userId);
}
