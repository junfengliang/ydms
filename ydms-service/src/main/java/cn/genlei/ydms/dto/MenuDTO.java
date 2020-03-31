package cn.genlei.ydms.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
public class MenuDTO {
    Integer id;
    Integer pid;
    String url;
    String permission;
    Integer type;
    String icon;
    String name;
    Integer orderNum;
}
