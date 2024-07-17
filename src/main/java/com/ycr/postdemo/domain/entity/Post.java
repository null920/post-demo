package com.ycr.postdemo.domain.entity;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ycr.postdemo.constant.UserRole;
import com.ycr.postdemo.domain.dto.PostCreateParam;
import com.ycr.postdemo.domain.dto.PostUpdateParam;
import com.ycr.postdemo.domain.dto.UserRegisterParam;
import lombok.Data;

/**
 * 帖子
 *
 * @TableName post
 */
@TableName(value = "post")
@Data
public class Post implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
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
    private String tags;

    /**
     * 点赞数
     */
    private Integer thumbNum;

    /**
     * 收藏数
     */
    private Integer favourNum;

    /**
     * 创建用户 id
     */
    private Long userId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer deleted;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    public Post add(PostCreateParam postCreateParam) {
        this.setTitle(postCreateParam.getTitle());
        this.setContent(postCreateParam.getContent());
        List<String> tagList = postCreateParam.getTags();
        if (tagList != null) {
            this.setTags(JSONUtil.toJsonStr(tagList));
        }
        return this;
    }

    public Post update(PostUpdateParam postUpdateParam) {
        this.setPostId(postUpdateParam.getPostId());
        this.setTitle(postUpdateParam.getTitle());
        this.setContent(postUpdateParam.getContent());
        List<String> tagList = postUpdateParam.getTags();
        if (tagList != null) {
            this.setTags(JSONUtil.toJsonStr(tagList));
        }
        return this;
    }
}