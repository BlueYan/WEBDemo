package com.mark.project.smis.query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 17/6/17.
 * 定义查询参数对象类 统一处理拼接sql语句的操作
 */
public abstract class QueryObject {

    protected List<Object> params = new ArrayList<Object>();
    protected List<String> conditions = new ArrayList<String>(); //声明一个条件集合 用于存储条件 解决where 1=1的问题

    //将分页查询的参数放父类中。主要是可能很多地方的查询都会用到分页

    private Integer currentPage;

    private Integer pageSize;

    public String query() {
        handleParams();
        StringBuilder sb = new StringBuilder();
        //循环迭代 拼接sql语句
        for ( int i = 0; i < conditions.size(); i++ ) {
            if ( i == 0 ) {
                //表示第一个条件
                sb.append("WHERE");
            } else {
                sb.append("AND");
            }
            sb.append(conditions.get(i));
        }
        System.out.println(sb);
        return sb.toString();
    }

    //自定义处理参数和条件等
    abstract public void handleParams();

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> params) {
        this.params = params;
    }

    public List<String> getConditions() {
        return conditions;
    }

    public void setConditions(List<String> conditions) {
        this.conditions = conditions;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
