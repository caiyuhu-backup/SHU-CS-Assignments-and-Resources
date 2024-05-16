package shu.java.csky.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    /**
     * 主键id;用户id
     */
    @TableId(type = IdType.AUTO)
    private String userId;

    /**
     * 用户名;用户名
     */
    private String username;

    /**
     * 密码;密码
     */
    private String password;

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

    /**
     * 注册时间;创建时间
     */
    private Date userRegtime;

    /**
     * 更新时间;更新时间
     */
    private Date userModtime;

}