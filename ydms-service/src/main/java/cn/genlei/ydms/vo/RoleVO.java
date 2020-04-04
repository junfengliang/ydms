package cn.genlei.ydms.vo;

import cn.genlei.ydms.entity.Role;
import lombok.Data;


/**
 * @author: Junfeng
 */
@Data
public class RoleVO extends Role {
    int[] menuIds;
}
