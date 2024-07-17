package com.ycr.postdemo.mapper;

import com.ycr.postdemo.domain.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author null&&
 * @description 针对表【post(帖子)】的数据库操作Mapper
 * @createDate 2024-07-16 16:34:02
 * @Entity com.ycr.postdemo.domain.entity.Post
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    /**
     * 根据帖子id 查询帖子
     *
     * @param postId 帖子id
     * @return 帖子详情
     */
    Post findPostById(Long postId);

    /**
     * 根据用户id 查询所有帖子
     *
     * @param userId 用户id
     * @return 帖子列表
     */
    List<Post> findPostListByUserId(Long userId);
}




