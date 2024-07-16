package com.ycr.postdemo.domain.entity.convertor;

import com.ycr.postdemo.common.UserInfo;
import com.ycr.postdemo.domain.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author null&&
 * @Date 2024/7/16 18:28
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserConvertor {
    UserConvertor INSTANCE = Mappers.getMapper(UserConvertor.class);

    /**
     * 转换为vo
     *
     * @param request
     * @return
     */
    @Mapping(target = "userId", source = "request.userId")
    public UserInfo mapToVo(User request);

    /**
     * 转换为实体
     *
     * @param request
     * @return
     */
    @Mapping(target = "userId", source = "request.userId")
    public User mapToEntity(UserInfo request);
}
