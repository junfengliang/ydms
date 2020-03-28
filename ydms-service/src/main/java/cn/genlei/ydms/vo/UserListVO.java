package cn.genlei.ydms.vo;

import cn.genlei.ydms.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author: Junfeng
 */
@Data
public class UserListVO {
    List<User> list;
    PageVO page;
}
