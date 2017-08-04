package com.mark.project.springDemo.day02.staticProxy.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark_Yan on 2017/8/4.
 */
@Setter
@Getter
@ToString
public class Person {

	private String name;

	public Person(){}

	public Person(String name) {
		this.name = name;
	}

}
