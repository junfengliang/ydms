package cn.genlei.ydms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResetDTO {
    String code;
    String password;
    String email;
}
