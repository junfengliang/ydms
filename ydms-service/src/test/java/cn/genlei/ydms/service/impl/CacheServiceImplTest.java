package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.dto.UserListDTO;
import cn.genlei.ydms.entity.User;
import cn.genlei.ydms.service.CacheService;
import cn.genlei.ydms.service.UserService;
import cn.genlei.ydms.vo.BaseVO;
import cn.genlei.ydms.vo.UserListVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

/**
 * @author: Junfeng
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheServiceImplTest {
    @Autowired
    CacheService cacheService;

    @Test
    public void set() {
        cacheService.set("test","val222",100, TimeUnit.SECONDS);
    }

    @Test
    public void get(){
        Object val = cacheService.get("test");
        System.out.println(val);
    }
    @Test
    public void genCodeKeyTest(){
        String key = cacheService.genCodeKey("uu@u.com");
        Assert.assertEquals("code_uu@u.com",key);
    }
}