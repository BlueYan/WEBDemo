package com.mark.project.employeeMS.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by Mark on 17/6/18.
 * 封装分页页面数据
 */
@Setter
@Getter
@ToString
public class PageResult<T> {

    private final Integer totalIndexCount = 10; //分页索引总数量

    //结果集
    private List<T> listData;

    //总条数
    private Integer totalCount;

    //总页数
    private Integer totalPage;

    //当前页
    private Integer currentPage;

    //上一页
    private Integer prePage;

    //下一页
    private Integer nextPage;

    //页面大小 每一页显示多少行
    private Integer pageSize;

    //分页索引
    private PageIndex pageIndex;

    public PageResult(List<T> listData, Integer totalCount, Integer currentPage, Integer pageSize) {
        this.listData = listData;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.totalPage = this.totalCount % this.pageSize == 0
                ? this.totalCount / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prePage = this.currentPage - 1 != 0 ? this.currentPage - 1 : 1;
        this.nextPage = this.currentPage + 1 <= this.totalPage ? this.currentPage + 1 : this.totalPage;
        this.pageIndex = PageIndex.getPageIndex(totalIndexCount, currentPage, totalPage);
    }

}
