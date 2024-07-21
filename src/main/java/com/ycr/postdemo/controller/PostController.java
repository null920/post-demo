package com.ycr.postdemo.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ycr.postdemo.common.Result;
import com.ycr.postdemo.common.request.PostQueryRequest;
import com.ycr.postdemo.common.response.PostOperatorResponse;
import com.ycr.postdemo.common.response.PostQueryResponse;
import com.ycr.postdemo.domain.dto.PostCreateParam;
import com.ycr.postdemo.domain.dto.PostUpdateParam;
import com.ycr.postdemo.domain.vo.PostVO;
import com.ycr.postdemo.exception.errorcode.PostErrorCode;
import com.ycr.postdemo.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author null&&
 * @Date 2024/7/17 11:18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/")
    @SaCheckLogin
    public Result<Boolean> createPost(@Valid @RequestBody PostCreateParam postCreateParam) {
        PostOperatorResponse createPostResult = postService.createPost(postCreateParam);
        if (Boolean.TRUE.equals(createPostResult.getSuccess())) {
            return Result.success(true);
        }
        return Result.error(createPostResult.getResponseCode(), createPostResult.getResponseMessage());
    }

    @GetMapping
    public Result<Page<PostVO>> getPostListByUserId(@RequestParam(value = "userId") Long userId) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setUserId(userId);
        PostQueryResponse<Page<PostVO>> postListByUserIdResult = postService.getPostListByUserId(postQueryRequest);
        if (Boolean.TRUE.equals(postListByUserIdResult.getSuccess())) {
            return Result.success(postListByUserIdResult.getData());
        }
        return Result.error(postListByUserIdResult.getResponseCode(), postListByUserIdResult.getResponseMessage());
    }

    @GetMapping("/{postId}")
    public Result<PostVO> getPostById(@PathVariable("postId") Long postId) {
        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setPostId(postId);
        PostQueryResponse<PostVO> postByIdResult = postService.getPostById(postQueryRequest);
        if (Boolean.TRUE.equals(postByIdResult.getSuccess())) {
            return Result.success(postByIdResult.getData());
        }
        return Result.error(postByIdResult.getResponseCode(), postByIdResult.getResponseMessage());
    }

    @PutMapping("/{postId}")
    @SaCheckLogin
    public Result<Boolean> updatePost(@PathVariable("postId") Long postId, @RequestBody PostUpdateParam postUpdateParam) {
        postUpdateParam.setPostId(postId);
        Boolean updateResult = postService.updatePost(postUpdateParam);
        if (Boolean.TRUE.equals(updateResult)) {
            return Result.success(true);
        }
        return Result.error(PostErrorCode.POST_OPERATE_FAILED.getCode(), PostErrorCode.POST_OPERATE_FAILED.getMessage());
    }

    @DeleteMapping("/{postId}")
    @SaCheckLogin
    public Result<Boolean> deletePost(@PathVariable("postId") Long postId) {
        Boolean deleteResult = postService.deletePost(postId);
        if (Boolean.TRUE.equals(deleteResult)) {
            return Result.success(true);
        }
        return Result.error(PostErrorCode.POST_OPERATE_FAILED.getCode(), PostErrorCode.POST_OPERATE_FAILED.getMessage());
    }


}
