package com.ycr.postdemo.mapper;

import com.ycr.postdemo.domain.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author null&&
 * @description 针对表【post(帖子)】的数据库操作Mapper
 * @createDate 2024-07-16 16:34:02
 * @Entity com.ycr.postdemo.domain.entity.Post
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

}




