package cn.genlei.ydms.dto;

import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
public class RoleDTO {
    Integer id;
    String roleName;
    String remark;

    Integer[] menuIds;
}
