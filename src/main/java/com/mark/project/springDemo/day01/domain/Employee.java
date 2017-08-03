package com.mark.project.springDemo.day01.domain;

import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mark_Yan on 2017/8/3.
 *
 * 员工实体类
 * 属性都是基本类型
 */
@ToString
@Setter
public class Employee {

	private String name;

	private Integer age;

	private BigDecimal salary;

}
