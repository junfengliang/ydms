package cn.genlei.ydms.service.impl;

import cn.genlei.ydms.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

/**
 * @author: Junfeng
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSenderImpl javaMailSender;
    @Value("${spring.mail.username}")
    String from;
    @Value("${ydms.code.subject}")
    String subject;
    @Value("${ydms.code.text}")
    String text;

    @Override
    public void send(String subject, String text, String to) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(to);
        message.setFrom(from);
        if(log.isDebugEnabled()) {
            log.debug("from={}", from);
        }
        javaMailSender.send(message);
    }

    @Override
    public void sendCode(String to, String code) {
        String content = StringUtils.replace(text,"{code}",code);
        send(subject,content,to);
    }
}
