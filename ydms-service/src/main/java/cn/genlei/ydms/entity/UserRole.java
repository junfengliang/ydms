package cn.genlei.ydms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
@Entity
@Table(name = "sys_user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    Integer roleId;
    Integer userId;
    Date createTime;
    Date updateTime;
}
