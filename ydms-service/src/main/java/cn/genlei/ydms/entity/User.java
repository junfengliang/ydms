package cn.genlei.ydms.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author nid
 */
@Data
@Entity
@Table(name = "sys_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    String username;
    String password;
    String salt;
    String email;
    String mobile;
    Integer status;
    Date lastLoginTime;
    Date createTime;
    Date updateTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    List<Role> roleList;
}
