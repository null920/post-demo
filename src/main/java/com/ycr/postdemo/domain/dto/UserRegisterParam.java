package com.ycr.postdemo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户注册请求参数
 *
 * @author null&&
 * @Date 2024/7/16 16:53
 */
@Setter
@Getter
public class UserRegisterParam {
    /**
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 确认密码
     */
    @NotBlank(message = "确认密码不能为空")
    private String checkPassword;

    /**
     * 邮箱
     */
    private String email;
}
