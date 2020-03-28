package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.RoleDTO;
import cn.genlei.ydms.dto.RoleListDTO;
import cn.genlei.ydms.entity.Role;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.global.LocaleMessage;
import cn.genlei.ydms.global.StatusCode;
import cn.genlei.ydms.repository.RoleRepository;
import cn.genlei.ydms.service.RoleService;
import cn.genlei.ydms.utils.ReturnUtil;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.RoleListVO;
import cn.genlei.ydms.vo.UserListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author: Junfeng
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    LocaleMessage localeMessage;
    @Override
    public BaseVO list(RoleListDTO roleListDTO) {
        Sort sort = Sort.by("id").ascending();
        Pageable pageable = PageRequest.of(roleListDTO.getPage()-1,roleListDTO.getSize(),sort);
        Page<Role> page = roleRepository.findAll(pageable);
        RoleListVO vo = new RoleListVO();
        vo.setPage(ReturnUtil.page(page));
        vo.setList(page.getContent());
        return ReturnUtil.success(vo);
    }

    @Override
    public BaseVO add(RoleDTO roleDTO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDTO,role);
        role.setCreatetime(new Date());
        role.setUpdatetime(new Date());
        roleRepository.save(role);
        return ReturnUtil.success();
    }

    @Override
    public BaseVO edit(RoleDTO roleDTO) {
        Role role = roleRepository.getOne(roleDTO.getId());
        if(role==null){
            return ReturnUtil.error(StatusCode.INVALID_CONTENT,
                    localeMessage.getMessage("role.id.notfound"));
        }
        BeanUtils.copyProperties(roleDTO,role);
        role.setUpdatetime(new Date());
        roleRepository.save(role);
        return ReturnUtil.success();
    }

    @Override
    public BaseVO delete(int id) {
        roleRepository.deleteById(id);
        return ReturnUtil.success();
    }
}
