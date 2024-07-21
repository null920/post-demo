package com.ycr.postdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ycr.postdemo.common.UserInfo;
import com.ycr.postdemo.common.request.UserQueryRequest;
import com.ycr.postdemo.common.response.UserOperatorResponse;
import com.ycr.postdemo.common.response.UserQueryResponse;
import com.ycr.postdemo.domain.dto.UserLoginParam;
import com.ycr.postdemo.domain.dto.UserRegisterParam;
import com.ycr.postdemo.domain.entity.User;

/**
 * 用户服务接口
 *
 * @author null&&
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-07-16 16:38:27
 */
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param userRegisterParam 用户注册请求参数
     * @return 注册结果
     */
    UserOperatorResponse userRegister(UserRegisterParam userRegisterParam);


    /**
     * 用户登录
     *
     * @param userLoginParam 用户登录请求参数
     * @return 用户信息
     */
    UserInfo userLogin(UserLoginParam userLoginParam);

    /**
     * 查询用户
     *
     * @param userQueryRequest 用户查询请求
     * @return 用户查询响应
     */
    UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest);

    /**
     * 根据 id 获取用户信息
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User findUserById(Long userId);
}
