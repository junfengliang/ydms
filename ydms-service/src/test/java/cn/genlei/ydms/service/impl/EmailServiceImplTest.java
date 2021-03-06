package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

/**
 * @author: Junfeng
 */
//@SpringBootTest
//@RunWith(SpringRunner.class)
public class EmailServiceImplTest {
    @Autowired
    EmailService emailService;
    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    String from;

   // @Test
    public void send() {
        String subject = "email test";
        String text = " body 000999";
        String to = "18552208@qq.com";
        emailService.send(subject,text,to);
    }

  //  @Test
    public void  sendWithName() throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessageHelper.setSubject("测试邮件");
        mimeMessageHelper.setTo("18552208@qq.com");
        mimeMessageHelper.setFrom(from,"张三");
        mimeMessageHelper.setText("content" + System.currentTimeMillis(), true);
        javaMailSender.send(mimeMessage);

    }

}