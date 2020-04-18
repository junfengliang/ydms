package cn.genlei.ydms.service;

/**
 * @author: Junfeng
 */
public interface EmailService {
    public void send(String subject,String text, String to);
    public void sendCode(String to,String code);
}
