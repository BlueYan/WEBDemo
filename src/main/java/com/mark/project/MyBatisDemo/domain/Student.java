package com.mark.project.MyBatisDemo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/25.
 */
@Setter
@Getter
@ToString(exclude = "teachers")
public class Student {

	private Long id;

	private String name;

	private List<Teacher> teachers = new ArrayList<Teacher>();

}
