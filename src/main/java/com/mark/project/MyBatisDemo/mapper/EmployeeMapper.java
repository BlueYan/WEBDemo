package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Employee;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
public interface EmployeeMapper {

	void save(Employee employee);

	Employee get(Long id);

}
