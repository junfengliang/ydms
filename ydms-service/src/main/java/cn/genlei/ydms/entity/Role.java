package cn.genlei.ydms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
@Entity
@Table(name = "sys_role")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    String roleName;
    String remark;
    Date createtime;
    Date updatetime;
}
