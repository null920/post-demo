package com.ycr.postdemo.common.request;

import lombok.*;

/**
 * 帖子查询请求封装类
 *
 * @author null&&
 * @Date 2024/7/17 17:30
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostQueryRequest extends PageRequest {

    private Long userId;

    private Long postId;
}
