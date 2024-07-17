package com.ycr.postdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.StpUtil;
import com.ycr.postdemo.common.Result;
import com.ycr.postdemo.common.UserInfo;
import com.ycr.postdemo.common.request.UserQueryRequest;
import com.ycr.postdemo.common.response.UserOperatorResponse;
import com.ycr.postdemo.constant.UserRole;
import com.ycr.postdemo.domain.dto.UserLoginParam;
import com.ycr.postdemo.domain.dto.UserRegisterParam;
import com.ycr.postdemo.domain.entity.User;
import com.ycr.postdemo.domain.entity.convertor.UserConvertor;
import com.ycr.postdemo.domain.vo.LoginVO;
import com.ycr.postdemo.exception.UserException;
import com.ycr.postdemo.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static com.ycr.postdemo.exception.errorcode.UserErrorCode.USER_NOT_EXIST;


/**
 * @author null&&
 * @Date 2024/7/16 17:22
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("auth")
public class UserController {

    /**
     * 默认登录超时时间：3 天
     */
    private static final Integer DEFAULT_LOGIN_SESSION_TIMEOUT = 60 * 60 * 24 * 3;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result<Boolean> register(@Valid @RequestBody UserRegisterParam userRegisterParam) {
        UserOperatorResponse registerResult = userService.userRegister(userRegisterParam);
        if (Boolean.TRUE.equals(registerResult.getSuccess())) {
            return Result.success(true);
        }
        return Result.error(registerResult.getResponseCode(), registerResult.getResponseMessage());
    }

    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody UserLoginParam userLoginParam) {
        UserInfo userInfo = userService.userLogin(userLoginParam);
        //登录
        StpUtil.login(userInfo.getUserId(), new SaLoginModel()
                .setExtra("roles", Arrays.asList(UserRole.USER))
                .setIsLastingCookie(userLoginParam.getRememberMe())
                .setTimeout(DEFAULT_LOGIN_SESSION_TIMEOUT));
        StpUtil.getSession().set(userInfo.getUserId().toString(), userInfo);
        LoginVO loginVO = new LoginVO(userInfo);
        return Result.success(loginVO);
    }

    @GetMapping("/me")
    public Result<UserInfo> getMyselfInfo() {
        String userId = (String) StpUtil.getLoginId();
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserId(Long.valueOf(userId));
        User user = userService.findUserById(Long.valueOf(userId));
        if (user == null) {
            throw new UserException(USER_NOT_EXIST);
        }
        user.setPassword(null);
        return Result.success(UserConvertor.INSTANCE.mapToVo(user));
    }

    @PostMapping("/logout")
    @SaCheckLogin
    public Result<Boolean> logout() {
        StpUtil.logout();
        return Result.success(true);
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
