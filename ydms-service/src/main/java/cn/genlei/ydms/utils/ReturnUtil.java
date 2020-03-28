package cn.genlei.ydms.utils;

import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.PageVO;
import org.springframework.data.domain.Page;

/**
 *
 * @author nid
 */
public class ReturnUtil {

    public static BaseVO success(Object data) {
        BaseVO vo = new BaseVO();
        vo.setCode(StatusCode.SUCCESS);
        vo.setData(data);
        return vo;
    }
    public static BaseVO success() {
        return success(null);
    }
    public static BaseVO error(int code,String msg) {
        BaseVO vo = new BaseVO();
        vo.setCode(code);
        vo.setMsg(msg);
        return vo;
    }


    public static PageVO page(Page<?> page) {
        PageVO vo = new PageVO();
        vo.setSize(page.getPageable().getPageSize());
        vo.setPage(page.getPageable().getPageNumber()+1);
        vo.setTotalCount(page.getTotalElements());
        vo.setTotalPage(page.getTotalPages());
        return vo;
    }
}
