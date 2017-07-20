package com.mark.project.hibernateDemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mark_Yan on 2017/7/18.
 * 用户实体类
 */

@Getter
@Setter
@ToString
public class User {

	private Long id;

	private String name;

	private BigDecimal salary;

	private Date hiredate;
}
