package cn.genlei.ydms.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author nid
 */
@Data
@ApiModel(description = "基础返回类")
public class BaseVO<T> {
    @ApiModelProperty(required = true, value = "状态码",example = "200")
    public int code;

    @ApiModelProperty(required = true,value = "消息",example = "success")
    public String message;
    public T data;

}
