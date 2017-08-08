package com.mark.project.springDemo.day03.jdbc.dao.impl;

import com.mark.project.springDemo.day03.jdbc.dao.IEmployeeDAO;
import com.mark.project.springDemo.day03.jdbc.domain.Emp;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/7.
 */
public class EmployeeDAOImpl extends JdbcDaoSupport implements IEmployeeDAO {

	public void save(Emp emp) {
		getJdbcTemplate().update("INSERT INTO t_employee(name, salary, hireDate) VALUES (?, ?, ?)",
				emp.getName(), emp.getSalary(), emp.getHireDate());
	}

	public void delete(Long id) {
		getJdbcTemplate().update("DELETE FROM t_employee WHERE id = ?", id);
	}

	public void update(Emp e) {
		getJdbcTemplate().update("UPDATE t_employee SET salary = ? WHERE id = ?", e.getSalary(), e.getId());
	}

	public Emp get(Long id) {
		Emp emp = getJdbcTemplate().queryForObject("SELECT * FROM t_employee WHERE id = ?", new Object[]{id}, new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp e = new Emp();
				e.setId(rs.getLong("id"));
				e.setName(rs.getString("name"));
				e.setSalary(rs.getBigDecimal("salary"));
				e.setHireDate(rs.getDate("hireDate"));
				return e;
			}
		});
		return emp;
	}

	public List<Emp> list() {

		List<Emp> emps = getJdbcTemplate().query("SELECT * FROM t_employee", new RowMapper<Emp>() {
			public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
				Emp emp = new Emp();
				emp.setId(rs.getLong("id"));
				emp.setName(rs.getString("name"));
				emp.setSalary(rs.getBigDecimal("salary"));
				emp.setHireDate(rs.getDate("hireDate"));
				return emp;
			}
		});

		return emps;
	}
}
