package cn.genlei.ydms.dto;

import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
public class UserDTO {
    Integer id;
    String username;
    String email;
    Integer[] roleIds;
}
