package cn.genlei.ydms.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JwtUtilTest {

    JwtUtil jwtUtil;

    @Before
    public void init(){
        jwtUtil = new JwtUtil();
        jwtUtil.setExpire(15);
        jwtUtil.setSecret("1234");
        System.out.println("before");
    }

    @Test
    public void generate() {
        String token = jwtUtil.generate("123");
        String sub = jwtUtil.getSubject(token);

        Assert.assertEquals(sub,"123");
    }
}