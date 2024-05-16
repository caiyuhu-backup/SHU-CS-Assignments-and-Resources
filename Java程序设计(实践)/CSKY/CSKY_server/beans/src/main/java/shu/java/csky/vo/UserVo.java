package shu.java.csky.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
    /**
     * 主键id;用户id
     */
    private String userId;

    /**
     * 用户名;用户名
     */
    private String username;

    /**
     * 头像;头像
     */
    private String userImg;

    /**
     * 手机号;手机号
     */
    private String userMobile;

    /**
     * 邮箱地址;邮箱地址
     */
    private String userEmail;
}