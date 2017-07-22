package com.mark.project.hibernateDemo.mapping_relation.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/7/22.
 */
@Setter
@Getter
public class Department {

	private Long id;

	private String name;

	@Override
	public String toString() {
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
