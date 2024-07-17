package com.ycr.postdemo.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.ycr.postdemo.constant.UserRole;
import com.ycr.postdemo.domain.entity.User;
import com.ycr.postdemo.mapper.UserMapper;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 自定义权限加载接口实现类
 *
 * @author null&&
 * @Date 2024/7/17 16:31
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<String> getPermissionList(Object userId, String s) {
        return Collections.emptyList();
    }

    @Override
    public List<String> getRoleList(Object userId, String s) {
        User user = userMapper.findUserById(Long.valueOf((String) userId));
        UserRole userRole = user.getUserRole();
        return List.of(userRole.name());
    }
}
