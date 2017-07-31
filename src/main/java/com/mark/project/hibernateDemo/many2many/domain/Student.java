package com.mark.project.hibernateDemo.many2many.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/26.
 *
 * 学生实体类
 * 一个学生可以拥有多个老师
 *
 */
@Setter
@Getter
public class Student {

	private Long id;

	private String sName;

	private List<Teacher> teachers = new ArrayList<Teacher>();

}
