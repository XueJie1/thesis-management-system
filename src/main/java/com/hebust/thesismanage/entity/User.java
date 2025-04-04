package com.hebust.thesismanage.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    // 学号/工号
    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("role")
    private Integer role; //  外键关联 role 表，这里存储 role_id

    @TableField("phone")
    private String phone;

    @TableField("otp_secret")
    private String otpSecret;

    @TableField("created_at")
    private LocalDateTime createdAt;

    @TableField("last_login")
    private LocalDateTime lastLogin;
}
