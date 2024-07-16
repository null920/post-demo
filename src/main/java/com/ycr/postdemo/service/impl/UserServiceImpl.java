package com.ycr.postdemo.service.impl;

import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.postdemo.common.UserInfo;
import com.ycr.postdemo.common.request.UserQueryRequest;
import com.ycr.postdemo.common.response.UserOperatorResponse;
import com.ycr.postdemo.common.response.UserQueryResponse;
import com.ycr.postdemo.domain.dto.UserLoginParam;
import com.ycr.postdemo.domain.dto.UserRegisterParam;
import com.ycr.postdemo.domain.entity.User;
import com.ycr.postdemo.domain.entity.convertor.UserConvertor;
import com.ycr.postdemo.exception.user.UserErrorCode;
import com.ycr.postdemo.exception.user.UserException;
import com.ycr.postdemo.mapper.UserMapper;
import com.ycr.postdemo.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author null&&
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-07-16 16:38:27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param userRegisterParam 用户注册请求参数
     * @return 注册结果
     */
    @Override
    public UserOperatorResponse userRegister(UserRegisterParam userRegisterParam) {
        if (userMapper.findByUsername(userRegisterParam.getUsername()) != null) {
            throw new UserException(UserErrorCode.USER_NAME_EXIST);
        }
        User user = new User();
        user.register(userRegisterParam);
        UserOperatorResponse registerResult = new UserOperatorResponse();
        if (save(user)) {
            User findUser = userMapper.findByUsername(userRegisterParam.getUsername());
            Assert.notNull(findUser, UserErrorCode.USER_OPERATE_FAILED.getCode());
            registerResult.setSuccess(true);
        }
        return registerResult;
    }

    /**
     * 用户登录
     *
     * @param userLoginParam 用户登录请求参数
     * @return 用户信息
     */
    @Override
    public UserInfo userLogin(UserLoginParam userLoginParam) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUsername(userLoginParam.getUsername());
        UserQueryResponse<UserInfo> userQueryResponse = this.query(userQueryRequest);
        UserInfo userInfo = userQueryResponse.getData();
        if (userInfo == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        if (!userInfo.getPassword().equals(DigestUtil.md5Hex(userLoginParam.getPassword()))) {
            throw new UserException(UserErrorCode.USER_PASSWORD_ERROR);
        }
        return userInfo;
    }

    /**
     * 查询用户
     *
     * @param userQueryRequest 用户查询请求
     * @return 用户查询响应
     */
    @Override
    public UserQueryResponse<UserInfo> query(UserQueryRequest userQueryRequest) {
        User user = null;
        if (null != userQueryRequest.getUserId()) {
            user = userMapper.findUserById(userQueryRequest.getUserId());
        }

        if (StringUtils.isNotBlank(userQueryRequest.getUsername())) {
            user = userMapper.findByUsername(userQueryRequest.getUsername());
        }
        if (user == null) {
            throw new UserException(UserErrorCode.USER_NOT_EXIST);
        }
        UserQueryResponse<UserInfo> response = new UserQueryResponse();
        response.setSuccess(true);
        UserInfo userInfo = UserConvertor.INSTANCE.mapToVo(user);
        response.setData(userInfo);
        return response;
    }

    @Override
    public User findUserById(Long userId) {
        if (Objects.nonNull(userId)) {
            return userMapper.findUserById(userId);
        }
        return null;
    }
}




