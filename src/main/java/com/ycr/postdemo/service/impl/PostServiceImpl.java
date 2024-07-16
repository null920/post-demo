package com.ycr.postdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ycr.postdemo.domain.entity.Post;
import com.ycr.postdemo.service.PostService;
import com.ycr.postdemo.mapper.PostMapper;
import org.springframework.stereotype.Service;

/**
* @author null&&
* @description 针对表【post(帖子)】的数据库操作Service实现
* @createDate 2024-07-16 16:34:02
*/
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post>
    implements PostService{

}




