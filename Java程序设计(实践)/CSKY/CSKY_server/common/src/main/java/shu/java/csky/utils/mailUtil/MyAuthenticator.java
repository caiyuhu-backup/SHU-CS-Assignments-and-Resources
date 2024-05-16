package shu.java.csky.utils.mailUtil;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * @author 20121706
 */
public class MyAuthenticator extends Authenticator {
    private String userName; // 邮箱
    private String userPwd; // 密码（授权码）
    public MyAuthenticator(String userName, String userPwd) {
        this.userName = userName;
        this.userPwd = userPwd;
    }
    /**
     * 邮件服务器发送邮件时，进行身份验证
     * @return
     */
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(userName, userPwd);
    }
}
