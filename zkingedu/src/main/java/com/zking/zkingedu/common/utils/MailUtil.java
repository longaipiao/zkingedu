package com.zking.zkingedu.common.utils;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtil {
    public static void configMail (String mail,String value) throws Exception{
        Properties prop = new Properties();
        // 开启debug调试，以便在控制台查看
        prop.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        prop.setProperty("mail.host", "smtp.qq.com");
        // 发送服务器需要身份验证
        prop.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        prop.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 创建session
        Session session = Session.getInstance(prop);
        // 通过session得到transport对象
        Transport ts = session.getTransport();
        // 连接邮件服务器：邮箱类型，帐号，授权码代替密码（更安全）
        // =====================自行修改账号和授权码
        ts.connect("smtp.qq.com", "1196683507@qq.com", "vtwtwddfvznjbaga");// 后面的字符是授权码
        // 创建邮件

        Message message = createSimpleMail(session,mail,value);
        // 发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    public static MimeMessage createSimpleMail(Session session,String mail,String value) throws Exception {
        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress("1196683507@qq.com"));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        // 邮件的标题
        message.setSubject("zking在线课堂。您本次的验证码为："+value+"。如非本人操作，请无视");
        // 邮件的文本内容
        message.setContent("<font style='color:red'>zking在线课堂。您本次的验证码为:"+value+"。如非本人操作，请无视</font>", "text/html;charset=UTF-8");
        // 返回创建好的邮件对象
        return message;
    }
}

