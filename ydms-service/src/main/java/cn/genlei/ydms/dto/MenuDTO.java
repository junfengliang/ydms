package cn.genlei.ydms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Date;

/**
 * @author: Junfeng
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
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
