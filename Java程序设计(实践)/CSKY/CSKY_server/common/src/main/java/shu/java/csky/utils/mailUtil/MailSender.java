package shu.java.csky.utils.mailUtil;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * @author 20121706
 */
public class MailSender {
    public void sendMail(MailSendInfo mailSendInfo) {
        Message message = null;
        Session session = null;
        try {
            // 定义邮箱服务器配置
            Properties props = new Properties();
            // 163 邮件服务器地址
            props.put("mail.smtp.host", mailSendInfo.getServerHost());
            // 163 邮件服务器端口
            props.put("mail.smtp.port", mailSendInfo.getServerPort());
            // 163 邮件服务器认证属性
            props.put("mail.smtp.auth", mailSendInfo.getFlag());
            // 身份认证类
            MyAuthenticator authenticator = new MyAuthenticator(mailSendInfo.getUserName(), mailSendInfo.getUserPwd());
            // 创建session
            session = Session.getDefaultInstance(props, authenticator);
            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            // 创建message邮件对象
            message = new MimeMessage(session);
            // 设置发送方的邮箱地址
            Address from = new InternetAddress(mailSendInfo.getFromAddress());
            message.setFrom(from);
            // 设置发送时间
            message.setSentDate(new Date());

            // 判断接收方的邮箱地址
            if (null != mailSendInfo.getToAddress() && mailSendInfo.getToAddress().size() > 0) {
                // 定义数组
                Address[] addresses = new Address[mailSendInfo.getToAddress().size()];
                // 循环获取接收方的邮箱地址，并设置到对应的address数组中
                for (int i = 0; i < mailSendInfo.getToAddress().size(); i++) {
                    Address address = new InternetAddress(mailSendInfo.getToAddress().get(i));
                    addresses[i] = address;
                }
                // 设置接收方的邮箱地址
                message.setRecipients(Message.RecipientType.TO, addresses);
                // 设置邮件主题
                message.setSubject(mailSendInfo.getSubject());
                // 创建多媒体对象容器
                Multipart multipart = new MimeMultipart();
                // 创建正文内容
                BodyPart bodyPart = new MimeBodyPart();
                bodyPart.setContent(mailSendInfo.getContent(), "text/html;charset=utf-8");
                // 添加正文 （将正文内容设置到多媒体对象容器中）
                multipart.addBodyPart(bodyPart);

                // 获取所有的附件内容
                List<String> files = mailSendInfo.getAttachFileNames();
                // 判断是否包含附件内容
                if(null != files && files.size() > 0){
                    for(int i = 0; i < files.size(); i++){
                        // 获取对应的附件对象
                        File tempFile = new File(files.get(i));
                        // 判断附件是否存在
                        if(tempFile.exists()){
                            // 如果附件存在，创建附件对象
                            BodyPart attachPart = new MimeBodyPart();
                            attachPart.setDataHandler(new DataHandler(new FileDataSource(tempFile)));
                            // 设置文件名 （解决附件名乱码）
                            attachPart.setFileName(MimeUtility.encodeText(tempFile.getName()));
                            // 添加附件 （将附件内容添加到多媒体对象容器中）
                            multipart.addBodyPart(attachPart);
                        }
                    }
                }
                // 设置邮件内容
                message.setContent(multipart);
                // 发送邮件
                Transport.send(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
