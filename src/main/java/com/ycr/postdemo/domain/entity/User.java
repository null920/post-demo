package com.ycr.postdemo.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.ycr.postdemo.constant.UserRole;
import com.ycr.postdemo.domain.dto.UserRegisterParam;
import lombok.Data;

/**
 * 用户
 *
 * @TableName user
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * user_id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户角色：user/admin/ban
     */
    private UserRole userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date lastModified;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    public User register(UserRegisterParam userRegisterParam) {
        this.setUsername(userRegisterParam.getUsername());
        this.setPassword(DigestUtil.md5Hex(userRegisterParam.getPassword()));
        this.setEmail(userRegisterParam.getEmail());
        this.setUserRole(UserRole.USER);
        return this;
    }

}