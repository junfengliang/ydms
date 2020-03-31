package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.MenuDTO;
import cn.genlei.ydms.entity.Menu;
import cn.genlei.ydms.entity.Role;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.repository.MenuRepository;
import cn.genlei.ydms.service.MenuService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.MenuListVO;
import cn.genlei.ydms.vo.MenuVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: Junfeng
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuRepository menuRepository;
    @Autowired
    LocaleMessage localeMessage;
    @Override
    public BaseVO listLeft() {
        //TODO
        return null;
    }

    @Override
    public BaseVO listAll() {
        List<Menu> list = menuRepository.findAll();
        List<MenuVO> voList = voPatch(list);

        MenuListVO menuListVO = new MenuListVO();
        menuListVO.setMenuVOList(voList);

        return ReturnUtil.success(menuListVO);
    }

    private List<MenuVO> voPatch(List<Menu> list) {
        if(list==null){
            return null;
        }
        Map<Integer,List<MenuVO>> map = new HashMap<>();
        List<MenuVO> rootVO = new ArrayList<>();
        List<MenuVO> allVO = new ArrayList<>();

        for(Menu menu:list){
            MenuVO menuVO = new MenuVO();
            allVO.add(menuVO);
            BeanUtils.copyProperties(menu,menuVO);

            Integer pid = menu.getPid();
            if(pid==null || pid==0){
                rootVO.add(menuVO);
            }else{
                List<MenuVO> l = map.get(pid);
                if(l==null){
                    l = new ArrayList<>();
                    l.add(menuVO);
                    map.put(pid,l);
                }else {
                    l.add(menuVO);
                }
            }
        }

        allVO.forEach((value) ->{
            List<MenuVO> children = map.get(value.getId());
            if(children!=null){
                Collections.sort(children);
                value.setChildren(children);
            }
        });

        Collections.sort(rootVO);
        return rootVO;
    }


    @Override
    public BaseVO add(MenuDTO menuDTO) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuDTO,menu);
        menu.setCreateTime(new Date());
        menu.setUpdateTime(new Date());
        if(menu.getOrderNum()==null){
            menu.setOrderNum(0);
        }
        menuRepository.save(menu);
        return ReturnUtil.success();
    }

    @Override
    public BaseVO edit(MenuDTO menuDTO) {
        Menu menu = menuRepository.getOne(menuDTO.getId());
        if(menu==null){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,
                    localeMessage.getMessage("role.id.notfound"));
        }
        BeanUtils.copyProperties(menu,menu);
        menu.setUpdateTime(new Date());
        menuRepository.save(menu);
        return ReturnUtil.success();
    }

    @Override
    public BaseVO delete(int id) {
        menuRepository.deleteById(id);
        return ReturnUtil.success();
    }
}
