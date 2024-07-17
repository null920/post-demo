package com.ycr.postdemo.common.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author null&&
 * @Date 2024/7/17 17:39
 */
@Setter
@Getter
@ToString
public class PostQueryResponse<T> extends BaseResponse {
    private static final long serialVersionUID = 1L;
    private T data;
}
