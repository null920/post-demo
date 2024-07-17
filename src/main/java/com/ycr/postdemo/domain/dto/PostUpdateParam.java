package com.ycr.postdemo.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author null&&
 * @Date 2024/7/17 18:12
 */
@Setter
@Getter
public class PostUpdateParam {

    @NotBlank(message = "帖子id不能为空")
    private Long postId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 标签列表（json 数组）
     */
    private List<String> tags;
}
