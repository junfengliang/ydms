package cn.genlei.ydms.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
@Entity
@Table(name = "sys_menu")
public class Menu {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    Integer id;
    Integer pid;
    String url;
    String name;
    String permission;
    Integer type;
    String icon;
    Integer orderNum;
    Date createTime;
    Date updateTime;

}
