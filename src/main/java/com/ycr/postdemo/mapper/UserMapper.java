package com.ycr.postdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ycr.postdemo.domain.entity.User;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author null&&
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createDate 2024-07-16 16:38:27
 * @Entity com.ycr.postdemo.domain.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 根据用户名查询用户
     *
     * @param username 用户名
     * @return 用户操作响应
     */
    User findByUsername(@NotNull String username);

    /**
     * 根据用户id 查询用户
     *
     * @param userId 用户id
     * @return 用户信息
     */
    User findUserById(@NotNull Long userId);
}




