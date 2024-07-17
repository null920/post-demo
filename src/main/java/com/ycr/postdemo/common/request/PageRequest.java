package com.ycr.postdemo.common.request;

import com.ycr.postdemo.constant.PageConstant;
import lombok.Data;

/**
 * 分页请求
 *
 * @author null&&
 * @Date 2024/7/17 17:27
 */
@Data
public class PageRequest extends BaseRequest {
    /**
     * 当前页号
     */
    private int current = 1;

    /**
     * 页面大小
     */
    private int pageSize = 10;

    /**
     * 排序字段
     */
    private String sortField;

    /**
     * 排序顺序（默认升序）
     */
    private String sortOrder = PageConstant.SORT_ORDER_ASC;

}
