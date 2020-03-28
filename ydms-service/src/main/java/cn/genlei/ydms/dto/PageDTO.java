package cn.genlei.ydms.dto;

import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
public class PageDTO {
    int page;
    int size;
    String sort;
}
