package cn.genlei.ydms.vo;

import lombok.Data;

/**
 * @author: Junfeng
 */
@Data
public class PageVO {
    int page;
    int size;
    int totalPage;
    long totalCount;

}
