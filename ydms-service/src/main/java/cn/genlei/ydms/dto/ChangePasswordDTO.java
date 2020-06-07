package cn.genlei.ydms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChangePasswordDTO {
    String old;
    String password;
    String confirmPassword;
}
