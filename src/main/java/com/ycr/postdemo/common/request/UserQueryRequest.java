package com.ycr.postdemo.common.request;

import lombok.*;

/**
 * @author null&&
 * @Date 2024/7/16 18:13
 */
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryRequest extends BaseRequest {

    private Long userId;

    private String username;
}
