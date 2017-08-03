package com.mark.project.springDemo.day01.domain;

import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark_Yan on 2017/8/3.
 *
 * 学生实体类包含教师实体类
 * 在需要注入的实体类中提供setter方法
 */
@Setter
@ToString
public class Student {

	private Teacher teacher;

}
