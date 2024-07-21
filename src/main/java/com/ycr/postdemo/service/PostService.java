package com.ycr.postdemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ycr.postdemo.common.request.PostQueryRequest;
import com.ycr.postdemo.common.response.PostOperatorResponse;
import com.ycr.postdemo.common.response.PostQueryResponse;
import com.ycr.postdemo.domain.dto.PostCreateParam;
import com.ycr.postdemo.domain.dto.PostUpdateParam;
import com.ycr.postdemo.domain.entity.Post;
import com.ycr.postdemo.domain.vo.PostVO;

/**
 * @author null&&
 * @description 针对表【post(帖子)】的数据库操作Service
 * @createDate 2024-07-16 16:34:02
 */
public interface PostService extends IService<Post> {
    /**
     * 创建帖子
     *
     * @param postCreateParam 创建帖子参数
     * @return 帖子操作响应
     */
    PostOperatorResponse createPost(PostCreateParam postCreateParam);

    /**
     * 获取某个用户的所有帖子列表（分页）
     *
     * @param postQueryRequest 查询帖子参数
     * @return 帖子列表
     */
    PostQueryResponse<Page<PostVO>> getPostListByUserId(PostQueryRequest postQueryRequest);

    /**
     * 根据id 获取帖子详情
     *
     * @param postQueryRequest 查询帖子参数
     * @return
     */
    PostQueryResponse<PostVO> getPostById(PostQueryRequest postQueryRequest);

    /**
     * 更新帖子
     *
     * @param postUpdateParam 更新帖子参数
     * @return 更新结果
     */
    Boolean updatePost(PostUpdateParam postUpdateParam);

    /**
     * 删除帖子
     *
     * @param postId 删除帖子参数
     * @return 删除结果
     */
    Boolean deletePost(Long postId);
}
