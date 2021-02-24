package com.psx.social.util;

import com.psx.social.entity.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailConfig {
    @Autowired
    JavaMailSender sender;

    public void send(MailDTO mailDTO) {
        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom(Constants.MAIL_SERVER);
        //接收者
        mainMessage.setTo(mailDTO.getTo());
        //发送的标题
        mainMessage.setSubject(mailDTO.getTitle());
        //发送的内容
        mainMessage.setText(mailDTO.getText());
        sender.send(mainMessage);
    }

    public void sendMsg() {

    }

    public void sendRequest() {

    }
}
