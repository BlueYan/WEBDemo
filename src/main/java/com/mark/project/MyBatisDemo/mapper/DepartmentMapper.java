package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Department;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
public interface DepartmentMapper {

	void save(Department dept);

	Department get(Long id);

}
