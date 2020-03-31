package cn.genlei.ydms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
@Entity
@Table(name = "sys_role_menu")
public class RoleMenu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    Integer roleId;
    Integer menuId;
    Date createTime;
    Date updateTime;
}
