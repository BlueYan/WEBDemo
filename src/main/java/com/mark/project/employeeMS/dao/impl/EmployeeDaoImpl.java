package com.mark.project.employeeMS.dao.impl;

import com.mark.project.employeeMS.dao.IEmployeeDao;
import com.mark.project.employeeMS.domain.Employee;
import com.mark.project.employeeMS.page.PageResult;
import com.mark.project.employeeMS.query.EmployeeQuery;
import com.mark.project.handler.impl.BeanListResultSetHandler;
import com.mark.project.handler.impl.BeanResultSetHandler;
import com.mark.project.handler.impl.ScalarResultSetHandler;
import com.mark.project.util.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by Administrator on 2017/7/14.
 *
 * 员工dao实现层
 *
 */
public class EmployeeDaoImpl implements IEmployeeDao {

	/**
	 * 增加员工信息
	 *
	 * @param e
	 */
	public void save(Employee e) {
		String sql = "INSERT INTO t_employee(empName, empPassword, email, hireDate) values(?, ?, ?, ?)";
		JdbcTemplate.update(sql, new Object[]{e.getEmpName(), e.getEmpPassword(), e.getEmail(), e.getHireDate()});
	}

	/**
	 * 根据id删除员工信息
	 *
	 * @param id
	 */
	public void delete(Long id) {
		String sql = "DELETE FROM t_employee WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	/**
	 * 更新员工信息
	 *
	 * @param e
	 */
	public void update(Employee e) {
		String sql = "UPDATE t_employee SET empName = ?, empPassword = ?, email = ?, hireDate = ? WHERE id = ?";
		JdbcTemplate.update(sql, new Object[]{e.getEmpName(), e.getEmpPassword(), e.getEmail(),
		e.getHireDate(), e.getId()});
	}

	/**
	 * 根据用户id获取员工信息
	 *
	 * @param id
	 * @return
	 */
	public Employee get(Long id) {
		Employee e = null;
		String sql = "SELECT * FROM t_employee WHERE id = ?";
		e = JdbcTemplate.queryByGeneric(sql, new BeanResultSetHandler<Employee>(Employee.class), id);
		return e;
	}

	/**
	 * 获取所有的员工信息
	 *
	 * @return
	 */
	public List<Employee> list() {
		List<Employee> employees = new ArrayList<Employee>();
		String sql = "SELECT * FROM t_employee";
		employees = JdbcTemplate.queryByGeneric(sql, new BeanListResultSetHandler<Employee>(Employee.class));
		return employees;
	}

	/**
	 * 高级查询
	 *
	 * @param query
	 * @return
	 */
	public List<Employee> query(EmployeeQuery query) {
		String sql = "SELECT * FROM t_employee" + query.query();
		List<Employee> list = new ArrayList<Employee>();
		list = JdbcTemplate.queryByGeneric(sql, new BeanListResultSetHandler<Employee>(Employee.class), query.getParams().toArray());
		return list;
	}

	/**
	 * 分页查询
	 * 注意:
	 *     总条数是long类型
	 *     获取总条数sql先执行
	 * @param query
	 * @return
	 */
	public PageResult<Employee> pageQuery(EmployeeQuery query) {
		System.out.println(query);
		String condition = query.query();
		System.out.println("condition = " + condition);
		String sql = "SELECT COUNT(id) FROM t_employee" + condition;
		Long totalCount = JdbcTemplate.queryByGeneric(sql, new ScalarResultSetHandler<Long>(Long.class), query.getParams().toArray());
		sql = "SELECT * FROM t_employee" + condition + " limit ?, ?";
		query.getParams().add((query.getCurrentPage() - 1) * query.getPageSize());
		query.getParams().add(query.getPageSize());
		List<Employee> list = new ArrayList<Employee>();
		list = JdbcTemplate.queryByGeneric(sql, new BeanListResultSetHandler<Employee>(Employee.class),
				query.getParams().toArray());
		PageResult pr = new PageResult(list, totalCount.intValue(), query.getCurrentPage(), query.getPageSize());
		return pr;
	}
}
