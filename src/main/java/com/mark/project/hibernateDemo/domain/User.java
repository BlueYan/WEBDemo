package com.mark.project.hibernateDemo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Mark_Yan on 2017/7/18.
 * 用户实体类
 */

@Getter
@Setter
@ToString
public class User {

	private Long id;

	private String name;

	private BigDecimal salary;

	@DateTimeFormat(pattern = "yyyy-MM-dd") //时间格式
	//@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") //指定返回的json日期的格式以及时区
	private Date hiredate;

	public User() {}

	public User(Long id, String name) {
		this.id = id;
		this.name = name;
	}
}
