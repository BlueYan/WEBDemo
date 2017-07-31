package com.mark.project.hibernateDemo.many2many.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/26.
 * 教师实体类
 * 一个教师可以拥有多个学生
 */
@Setter
@Getter
public class Teacher {


	private Long id;

	private String tName;

	private List<Student> students = new ArrayList<Student>();

}
