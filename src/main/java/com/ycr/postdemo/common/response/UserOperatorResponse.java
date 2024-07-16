package com.ycr.postdemo.common.response;

import com.ycr.postdemo.common.UserInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户操作响应
 *
 * @author null&&
 * @Date 2024/7/16 17:59
 */
@Getter
@Setter
public class UserOperatorResponse extends BaseResponse {
    private UserInfo userInfo;
}
