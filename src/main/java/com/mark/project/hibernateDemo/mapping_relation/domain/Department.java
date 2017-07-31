package com.mark.project.hibernateDemo.mapping_relation.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/22.
 */
@Setter
@Getter
public class Department {

	private Long id;

	private String name;

	//部门表要维护员工表，需要添加多个员工，用集合来添加。
	//一个部门有多个员工
	private List<Employee> employees = new ArrayList<Employee>();

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
