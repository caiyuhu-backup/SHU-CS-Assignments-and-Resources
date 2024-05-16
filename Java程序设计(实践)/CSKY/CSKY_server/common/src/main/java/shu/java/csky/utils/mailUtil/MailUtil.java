package shu.java.csky.utils.mailUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 20121706
 */
public class MailUtil {
    public static void main(String[] args) {
        sendCode("adfa", "1610580113@qq.com", "adfsa");
    }

    public static void sendCode(String email, String code, String title) {
        MailSendInfo mailSendInfo = new MailSendInfo();
        mailSendInfo.setServerHost("smtp.163.com");
        mailSendInfo.setServerPort("25");
        mailSendInfo.setUserName("itjx_1610580113");
        mailSendInfo.setUserPwd("ZEBJSEPQZEQASWHB");
        mailSendInfo.setSubject(title);
        mailSendInfo.setFromAddress("itjx_1610580113@163.com");
        mailSendInfo.setContent("<h2>" + code + "</h2>");
        List<String> users = new ArrayList<>();
        users.add(email);
        mailSendInfo.setToAddress(users);
        MailSender mailSender = new MailSender();
        mailSender.sendMail(mailSendInfo);
    }
}
