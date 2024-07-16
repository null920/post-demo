package com.ycr.postdemo.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author null&&
 * @Date 2024/7/16 17:12
 */
@Getter
@Setter
@NoArgsConstructor
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
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
    private String userRole;
}
