package com.ycr.postdemo.common.response;

import com.ycr.postdemo.common.PostInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 帖子操作响应
 *
 * @author null&&
 * @Date 2024/7/17 17:06
 */
@Getter
@Setter
public class PostOperatorResponse extends BaseResponse {
    private PostInfo postInfo;
}
