package com.ycr.postdemo.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.postdemo.common.request.PostQueryRequest;
import com.ycr.postdemo.common.response.PostOperatorResponse;
import com.ycr.postdemo.common.response.PostQueryResponse;
import com.ycr.postdemo.constant.PageConstant;
import com.ycr.postdemo.constant.UserRole;
import com.ycr.postdemo.domain.dto.PostCreateParam;
import com.ycr.postdemo.domain.dto.PostUpdateParam;
import com.ycr.postdemo.domain.entity.Post;
import com.ycr.postdemo.domain.entity.User;
import com.ycr.postdemo.domain.entity.convertor.PostConvertor;
import com.ycr.postdemo.domain.vo.PostVO;
import com.ycr.postdemo.exception.PostException;
import com.ycr.postdemo.exception.errorcode.PostErrorCode;
import com.ycr.postdemo.mapper.PostMapper;
import com.ycr.postdemo.mapper.UserMapper;
import com.ycr.postdemo.service.PostService;
import com.ycr.postdemo.utils.SqlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author null&&
 * @description 针对表【post(帖子)】的数据库操作Service实现
 * @createDate 2024-07-16 16:34:02
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
        implements PostService {

    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 创建帖子
     *
     * @param postCreateParam 创建帖子参数
     * @return 帖子操作响应
     */
    @Override
    public PostOperatorResponse createPost(PostCreateParam postCreateParam) {
        Post post = new Post();
        post.add(postCreateParam);
        post.setUserId(Long.valueOf((String) StpUtil.getLoginId()));
        PostOperatorResponse createPostResult = new PostOperatorResponse();
        if (save(post)) {
            Post findPost = postMapper.findPostById(post.getPostId());
            Assert.notNull(findPost, PostErrorCode.POST_OPERATE_FAILED.getCode());
            createPostResult.setSuccess(true);
        }
        return createPostResult;
    }

    @Override
    public PostQueryResponse<Page<PostVO>> getPostListByUserId(PostQueryRequest postQueryRequest) {
        int current = postQueryRequest.getCurrent();
        int pageSize = postQueryRequest.getPageSize();
        String sortField = postQueryRequest.getSortField();
        String sortOrder = postQueryRequest.getSortOrder();
        Long userId = postQueryRequest.getUserId();

        if (null == userId) {
            throw new PostException(PostErrorCode.POST_QUERY_PARAM_ERROR);
        }
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(PageConstant.SORT_ORDER_ASC), sortField);

        Page<Post> postPage = postMapper.selectPage(new Page<>(current, pageSize), queryWrapper);
        Page<PostVO> postInfoPage = new Page<>(postPage.getCurrent(), postPage.getSize(), postPage.getTotal());
        //postInfoPage.setPages(postPage.getPages());
        postInfoPage.setRecords(postPage.getRecords().stream()
                .map(post -> new PostVO(PostConvertor.INSTANCE.mapToVo(post)))
                .toList());
        PostQueryResponse<Page<PostVO>> response = new PostQueryResponse<>();
        if (postInfoPage.getTotal() == 0) {
            response.setSuccess(true);
            response.setResponseMessage("列表为空");
            response.setData(null);
            return response;
        }
        response.setSuccess(true);
        response.setData(postInfoPage);
        return response;
    }

    @Override
    public PostQueryResponse<PostVO> getPostById(PostQueryRequest postQueryRequest) {
        Post post;
        Long postId = postQueryRequest.getPostId();
        if (null == postId) {
            throw new PostException(PostErrorCode.POST_QUERY_PARAM_ERROR);
        }
        post = postMapper.findPostById(postId);
        PostQueryResponse<PostVO> response = new PostQueryResponse<>();
        if (post == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        response.setSuccess(true);
        PostVO postVO = new PostVO(PostConvertor.INSTANCE.mapToVo(post));
        response.setData(postVO);
        return response;
    }

    @Override
    public Boolean updatePost(PostUpdateParam postUpdateParam) {
        Long postId = postUpdateParam.getPostId();
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Post postById = postMapper.findPostById(postId);
        User userById = userMapper.findUserById(loginId);
        if (postById == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        if (!postById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new PostException(PostErrorCode.POST_OPERATE_NO_AUTH);
        }
        Post post = new Post();
        post.update(postUpdateParam);
        if (postMapper.updateById(post) == 0) {
            throw new PostException(PostErrorCode.POST_OPERATE_FAILED);
        }
        return true;
    }

    @Override
    public Boolean deletePost(Long postId) {
        Long loginId = Long.valueOf((String) StpUtil.getLoginId());
        Post postById = postMapper.findPostById(postId);
        User userById = userMapper.findUserById(loginId);
        if (postById == null) {
            throw new PostException(PostErrorCode.POST_NOT_EXIST);
        }
        if (!postById.getUserId().equals(loginId) && !userById.getUserRole().equals(UserRole.ADMIN)) {
            throw new PostException(PostErrorCode.POST_OPERATE_NO_AUTH);
        }
        if (postMapper.deleteById(postId) == 0) {
            throw new PostException(PostErrorCode.POST_OPERATE_FAILED);
        }
        return true;
    }
}




