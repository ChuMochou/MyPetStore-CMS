package org.csu.petstore.common.dto;

import java.io.Serializable;

/**
 * 分页查询参数 DTO
 */
public class PageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页码（从 1 开始）
     */
    private Integer pageNum;

    /**
     * 每页显示条数
     */
    private Integer pageSize;

    public PageQuery() {
        this.pageNum = 1;
        this.pageSize = 10;
    }

    public PageQuery(Integer pageNum, Integer pageSize) {
        this.pageNum = pageNum == null ? 1 : pageNum;
        this.pageSize = pageSize == null ? 10 : pageSize;
    }

    /**
     * 获取偏移量（用于 MyBatis 分页）
     * @return 偏移量
     */
    public Integer getOffset() {
        return (pageNum - 1) * pageSize;
    }

    // Getters and Setters

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
