package yuzhaoLiu.project.controller.mybatis.people.sendMailUtil;

import com.sun.mail.util.MailSSLSocketFactory;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil implements Runnable {
    private String email;// 收件人邮箱
    private String code;// 激活码
    private String username;
    private String password;

    public MailUtil(String email, String code , String username , String password) {
        this.email = email;
        this.code = code;
        this.username = username;
        this.password = password;
    }

    public void run() {
        // 1.创建连接对象javax.mail.Session
        // 2.创建邮件对象 javax.mail.Message
        // 3.发送一封激活邮件
        String from = "1844927304@qq.com";// 发件人电子邮箱
        String host = "smtp.qq.com"; // 指定发送邮件的主机smtp.qq.com(QQ)|smtp.163.com(网易)

        Properties properties = System.getProperties();// 获取系统属性

        properties.setProperty("mail.smtp.host", host);// 设置邮件服务器
        properties.setProperty("mail.smtp.auth", "true");// 打开认证

        try {
            //QQ邮箱需要下面这段代码，163邮箱不需要
            /**SSL认证，注意腾讯邮箱是基于SSL加密的，所有需要开启才可以使用**/
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.ssl.socketFactory", sf);

            //设置发件人名称
            String nick=javax.mail.internet.MimeUtility.encodeText("sevenZero");

            // 1.获取默认session对象 zgkpiofsttqccchc ttdngjvzaxjrbjdj mconzknikqkveiaa
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("1844927304@qq.com", "qqsjuwalpzqhgaae"); // 发件人邮箱账号、授权码
                }
            });

            // 2.创建邮件对象
            Message message = new MimeMessage(session);
            // 2.1设置发件人
            message.setFrom(new InternetAddress(nick+" <"+from+">"));
            // 2.2设置接收人
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            // 2.3设置邮件主题
            message.setSubject("账号激活");
            // 2.4设置邮件内容
            String content = "<html><head></head><body><h1>这是一封激活邮件,激活请点击以下链接</h1>" +
                    "<h3><a href='http://www.617museum.top/617/Zed"+username+".617museum'>点我点我>()<</a></h3></body></html>";
            message.setContent(content, "text/html;charset=UTF-8");
            /*www.617museum.top*//*http://www.617museum.top/users/usersLogin?username=77&&password=617572*/
            /*localhost:8080*/
            // 3.发送邮件
            Transport.send(message);
            System.out.println("邮件成功发送!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}