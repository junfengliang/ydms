package cn.genlei.ydms.vo;

import lombok.Data;

import java.util.List;

/**
 * @author nid
 */
@Data
public class UserInfoVO {
    List<String> roles;
    String introduction;
    String avatar;
    String name;
}
