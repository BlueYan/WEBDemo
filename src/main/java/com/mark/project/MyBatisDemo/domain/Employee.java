package com.mark.project.MyBatisDemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
@Setter
@Getter
@ToString(exclude = "dept")
public class Employee {

	private Long id;

	private String name;

	private Department dept;
}
