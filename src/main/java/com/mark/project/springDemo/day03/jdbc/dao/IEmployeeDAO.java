package com.mark.project.springDemo.day03.jdbc.dao;

import com.mark.project.springDemo.day03.jdbc.domain.Emp;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
public interface IEmployeeDAO {


	void save(Emp emp);

	void delete(Long id);

	void update(Emp e);

	Emp get(Long id);

	List<Emp> list();

}
