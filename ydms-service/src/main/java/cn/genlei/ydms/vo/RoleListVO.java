package cn.genlei.ydms.vo;

import cn.genlei.ydms.entity.Role;
import lombok.Data;

import java.util.List;

/**
 * @author: Junfeng
 */
@Data
public class RoleListVO {
    List<Role> list;
    PageVO page;
}
