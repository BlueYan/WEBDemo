package com.mark.project.employeeMS.dao;

import com.mark.project.employeeMS.domain.Employee;
import com.mark.project.employeeMS.page.PageResult;
import com.mark.project.employeeMS.query.EmployeeQuery;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 *
 * Employee dao接口
 * 数据库文件: resource/db/t_employee.sql
 *
 */
public interface IEmployeeDao {

	/**
	 * 增加员工信息
	 * @param e
	 */
	void save(Employee e);

	/**
	 * 根据id删除员工信息
	 * @param id
	 */
	void delete(Long id);

	/**
	 * 更新员工信息
	 * @param e
	 */
	void update(Employee e);

	/**
	 * 根据用户id获取员工信息
	 * @param id
	 * @return
	 */
	Employee get(Long id);

	/**
	 * 获取所有的员工信息
	 * @return
	 */
	List<Employee> list();

	/**
	 * 高级查询
	 * @param query
	 * @return
     */
	List<Employee> query(EmployeeQuery query);

	/**
	 * 分页查询
	 * @param query
	 * @return
     */
	PageResult<Employee> pageQuery(EmployeeQuery query);
}
