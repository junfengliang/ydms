package cn.genlei.ydms.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nid
 */
@Data
@ApiModel(description = "登录传参")
public class LoginDTO {

    @ApiModelProperty(required = true, value = "用户名",example = "admin")
    String username;

    @ApiModelProperty(required = true, value = "密码",example = "xxxxxx")
    String password;
}
