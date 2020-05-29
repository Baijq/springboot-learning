package com.api.example.apiexample.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页结果数据bean
 *
 * @author wbbaijq
 */
@Data
@Accessors(chain = true)
public class PageResultDataBean {

    /** 总条数 */
    private Long total;

    /** 每页大小 */
    private Integer size;

    /** 页数 */
    private Long pages;

    /** 当前页 */
    private Long current;

    /** 数据 */
    private List<?> data;
}
