package cn.genlei.ydms.entity;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;

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
    Integer createUserId;
    Date createtime;
    Date lastLoginTime;
    Date updatetime;



}
