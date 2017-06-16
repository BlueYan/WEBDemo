package com.mark.project.smis.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/16.
 * 定义学生类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

	private int id;

	private String name;

	private int age;

}

