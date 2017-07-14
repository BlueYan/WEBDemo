package com.mark.project.employeeMS.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/14.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Long id;

	private String empName;

	private String empPassword;

	private String email;

	private Date hireDate;
}
