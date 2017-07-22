package com.mark.project.hibernateDemo.mapping_relation.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/7/22.
 */
@Setter
@Getter
public class Employee {

	private Long id;

	private String name;

	private Department dept; //多对一映射 one方的主键要存在与多方的表中。

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
