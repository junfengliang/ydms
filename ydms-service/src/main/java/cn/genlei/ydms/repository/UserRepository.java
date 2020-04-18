package cn.genlei.ydms.repository;

import cn.genlei.ydms.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The interface User repository.
 *
 * @author nid
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * find user by username
     *
     * @param userName the user name
     * @return user
     */
    User findByUsername(String userName);

    /**
     * Find by email optional.
     *
     * @param email the user's email
     * @return the optional
     */
    Optional<User> findByEmail(String email);


}
