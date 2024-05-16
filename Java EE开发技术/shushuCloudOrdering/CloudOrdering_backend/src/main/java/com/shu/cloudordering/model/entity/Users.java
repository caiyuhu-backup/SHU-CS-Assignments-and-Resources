package com.shu.cloudordering.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户 
 * @TableName users
 */
@TableName(value ="users")
@Data
public class Users implements Serializable {
    /**
     * 主键id 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    /**
     * 用户名 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 密码 密码
     */
    @TableField(value = "password")
    private String password;

    /**
     * 昵称 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 真实姓名 真实姓名
     */
    @TableField(value = "realname")
    private String realname;

    /**
     * 头像 头像
     */
    @TableField(value = "user_img")
    private String userImg;

    /**
     * 手机号 手机号
     */
    @TableField(value = "user_mobile")
    private String userMobile;

    /**
     * 邮箱地址 邮箱地址
     */
    @TableField(value = "user_email")
    private String userEmail;

    /**
     * 性别 M(男) or F(女)
     */
    @TableField(value = "user_sex")
    private String userSex;

    /**
     * 生日 生日
     */
    @TableField(value = "user_birth")
    private Date userBirth;

    /**
     * 注册时间 创建时间
     */
    @TableField(value = "user_regtime")
    private Date userRegtime;

    /**
     * 更新时间 更新时间
     */
    @TableField(value = "user_modtime")
    private Date userModtime;

    /**
     * 是否为商家，默认为0，如果是1则为商家
     */
    @TableField(value = "vendor_id")
    private Integer vendorId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}