package com.ycr.postdemo.domain.entity.convertor;

import com.ycr.postdemo.common.PostInfo;
import com.ycr.postdemo.domain.entity.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

/**
 * @author null&&
 * @Date 2024/7/17 17:41
 */
@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PostConvertor {
    PostConvertor INSTANCE = Mappers.getMapper(PostConvertor.class);

    /**
     * 转换为vo
     *
     * @param request
     * @return
     */
    @Mapping(target = "postId", source = "request.postId")
    public PostInfo mapToVo(Post request);

    /**
     * 转换为实体
     *
     * @param request
     * @return
     */
    @Mapping(target = "postId", source = "request.postId")
    public Post mapToEntity(PostInfo request);
}
