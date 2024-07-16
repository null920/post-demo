package com.ycr.postdemo.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author null&&
 * @Date 2024/7/16 18:18
 */
@Setter
@Getter
@ToString
public class UserQueryResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;

    private T data;
}
