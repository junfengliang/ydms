package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.RoleDTO;
import cn.genlei.ydms.dto.RoleListDTO;
import cn.genlei.ydms.entity.Role;
import cn.genlei.ydms.entity.RoleMenu;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.repository.RoleMenuRepository;
import cn.genlei.ydms.repository.RoleRepository;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author: Junfeng
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMenuRepository roleMenuRepository;

    @Autowired
    LocaleMessage localeMessage;
    @Override
    public BaseVO<RoleListVO> list(RoleListDTO roleListDTO) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(roleListDTO.getPage()-1,roleListDTO.getSize(),sort);
        Page<Role> page = roleRepository.findAll(pageable);
        RoleListVO vo = new RoleListVO();
        vo.setPage(ReturnUtil.page(page));
        vo.setList(page.getContent());
        return ReturnUtil.success(vo);
    }

    @Override
    public BaseVO<Role> add(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role = roleRepository.save(role);
        saveRoleMenu(role.getId(),roleDTO.getMenuIds());
        return ReturnUtil.success(role);
    }

    private void saveRoleMenu(Integer roleId, Integer[] menuIds) {
        if(menuIds==null){
            if(log.isDebugEnabled()){
                log.debug("routes is null");
            }
            return;
        }
        List<RoleMenu> list = roleMenuRepository.findAllByRoleId(roleId);
        List<RoleMenu> tobeDelete = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        if(list!=null){
            for(RoleMenu roleMenu:list){
                if(Arrays.binarySearch(menuIds,roleMenu.getMenuId())<0){
                    tobeDelete.add(roleMenu);
                }
                set.add(roleMenu.getMenuId());
            }
        }
        List<RoleMenu> tobeAdd = new ArrayList<>();

        for(Integer menuId : menuIds){
            if(!set.contains(menuId)){
                tobeAdd.add(generateRoleMenu(menuId,roleId));
            }
        }
        if(tobeDelete.size()>0){
            roleMenuRepository.deleteAll(tobeDelete);
        }
        if(tobeAdd.size()>0){
            roleMenuRepository.saveAll(tobeAdd);
        }
        if(log.isDebugEnabled()){
            log.debug("save role menu, delete[{}],add[{}]"
                    ,tobeDelete.size(),tobeAdd.size());
        }
    }

    private RoleMenu generateRoleMenu(Integer menuId, Integer roleId) {
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setCreateTime(new Date());
        roleMenu.setUpdateTime(new Date());
        roleMenu.setRoleId(roleId);
        roleMenu.setMenuId(menuId);

        return roleMenu;
    }

    @Override
    public BaseVO<Role> edit(RoleDTO roleDTO) {
        Role role = roleRepository.getOne(roleDTO.getId());
        if(role==null){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,
                    localeMessage.getMessage("role.id.notfound"));
        }
        BeanUtils.copyProperties(roleDTO,role);
        role.setUpdateTime(new Date());
        roleRepository.save(role);
        saveRoleMenu(role.getId(),roleDTO.getMenuIds());
        return ReturnUtil.success(role);
    }

    @Override
    public BaseVO delete(int id) {
        roleMenuRepository.deleteByRoleId(id);
        roleRepository.deleteById(id);
        return ReturnUtil.success();
    }

    @Override
    public RoleVO detail(int id) {
        Role role = roleRepository.getOne(id);
        RoleVO roleVO = new RoleVO();
        BeanUtils.copyProperties(role,roleVO);
        List<RoleMenu> roleMenuList = roleMenuRepository.findAllByRoleId(id);
        roleVO.setMenuIds(roleMenuList.stream().mapToInt(item -> item.getMenuId()).toArray());
        return roleVO;
    }
}
