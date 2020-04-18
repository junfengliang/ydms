package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.service.CacheService;
import cn.genlei.ydms.service.EmailService;
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
public class EmailServiceImplTest {
    @Autowired
    EmailService emailService;

    @Test
    public void send() {
        String subject = "email test";
        String text = " body 000999";
        String to = "18552208@qq.com";
        emailService.send(subject,text,to);
    }

}