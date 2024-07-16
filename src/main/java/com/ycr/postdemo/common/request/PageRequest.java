package com.ycr.postdemo.common.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author null&&
 * @Date 2024/7/16 18:16
 */
@Setter
@Getter
public class PageRequest extends BaseRequest {
    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    private int currentPage;
    /**
     * 每页结果数
     */
    private int pageSize;
}
