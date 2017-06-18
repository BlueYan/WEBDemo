package com.mark.project.smis.query;

import com.mark.project.util.StringUtil;

/**
 * Created by Mark on 17/6/17.
 * 学生参数访问对象类
 * 继承于父类 实现handleParams方法 处理参数和条件
 */
public class StudentQueryObject extends QueryObject {

    private String name;

    private Integer minAge;

    private Integer maxAge;

    public void handleParams() {
        if ( StringUtil.isNotEmpty(name) ) {
            params.add("%" + name + "%"); //将需要传递到sql语句的参数保存起来
            conditions.add(" name LIKE ? "); //将sql语句中的条件保存起来
        }
        if ( minAge != null ) {
            params.add(minAge);
            conditions.add(" age >= ? ");
        }
        if ( maxAge != null ) {
            params.add(maxAge);
            conditions.add(" age <= ? ");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }
}
