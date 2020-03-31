package cn.genlei.ydms.vo;

import cn.genlei.ydms.entity.Menu;
import lombok.Data;

import java.util.List;

/**
 * @author: Junfeng
 */
@Data
public class MenuVO extends Menu implements Comparable<MenuVO> {
    List<MenuVO> children;

    @Override
    public int compareTo(MenuVO o) {
        int i = Integer.compare(getOrderNum(),o.getOrderNum());
        if(i==0){
            i = Integer.compare(getId(),o.getId());
        }
        return i;
    }
}
