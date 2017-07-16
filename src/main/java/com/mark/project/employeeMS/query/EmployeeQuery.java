package com.mark.project.employeeMS.query;

import com.mark.project.util.CommonUtil;
import com.mysql.jdbc.NotUpdatable;

/**
 * Created by Mark on 17/7/15.
 */
public class EmployeeQuery extends QueryObject {

    private String name;

    private String lowerDate; //最小入职时间

    private String higherDate; //最大入职时间

    public EmployeeQuery() {
    }

    public EmployeeQuery(String name, String lowerDate, String higherDate) {
        this.name = name;
        this.lowerDate = lowerDate;
        this.higherDate = higherDate;
    }

    public String getName() {
        return name;
    }

    public String getLowerDate() {
        return lowerDate;
    }

    public String getHigherDate() {
        return higherDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLowerDate(String lowerDate) {
        this.lowerDate = lowerDate;
    }

    public void setHigherDate(String higherDate) {
        this.higherDate = higherDate;
    }

    public void handleParams() {
        if (CommonUtil.isNotEmpty(name) ) {
            params.add("%" + name + "%");
            conditions.add("empName LIKE ?"); //添加条件
        }
        if ( CommonUtil.isNotEmpty(lowerDate) ) {
            params.add(lowerDate);
            conditions.add("hireDate >= ?");
        }
        if ( CommonUtil.isNotEmpty(higherDate) ) {
            params.add(higherDate);
            conditions.add("hireDate <= ?");
        }
    }

    @Override
    public String toString() {
        return "EmployeeQuery{" +
                "name='" + name + '\'' +
                ", lowerDate='" + lowerDate + '\'' +
                ", higherDate='" + higherDate + '\'' +
                '}';
    }
}
