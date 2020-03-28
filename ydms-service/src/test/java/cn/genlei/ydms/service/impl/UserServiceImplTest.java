package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.UserListDTO;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.UserListVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author: Junfeng
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    public void list() {
        UserListDTO dto = new UserListDTO();
        dto.setPage(1);
        dto.setSize(3);
        BaseVO vo = userService.list(dto);
        UserListVO userListVO = (UserListVO) vo.getData();
        System.out.println(userListVO.getList().size());
        for(User user:userListVO.getList()){
            System.out.println(user);
        }
        System.out.println(vo.toString());
    }
}