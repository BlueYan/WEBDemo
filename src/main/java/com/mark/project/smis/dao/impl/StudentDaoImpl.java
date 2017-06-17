package com.mark.project.smis.dao.impl;

import com.mark.project.handler.impl.BeanListResultSetHandler;
import com.mark.project.handler.impl.BeanResultSetHandler;
import com.mark.project.handler.impl.ScalarResultSetHandler;
import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.domain.Student;
import com.mark.project.util.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 *
 * 定义学生DAO实现类
 *
 */
public class StudentDaoImpl implements IStudentDao {


	public List<Student> list() {
		List<Student> list = new ArrayList<Student>();
		String sql = "SELECT * FROM t_student";
		list = JdbcTemplate.queryByGeneric(sql, new BeanListResultSetHandler<Student>(Student.class));
		return list;
	}

	public void delete(int id) {
		String sql = "DELETE FROM t_student WHERE id = ?";
		JdbcTemplate.update(sql, id);
	}

	public void add(Student stu) {
		String sql = "INSERT INTO t_student(name, age) values(?, ?)";
		JdbcTemplate.update(sql, stu.getName(), stu.getAge());
	}

	public void update(Student stu) {
		String sql = "UPDATE t_student SET name = ?, age = ? WHERE id = ?";
		JdbcTemplate.update(sql, stu.getName(), stu.getAge(), stu.getId());
	}

	public Student get(int id) {
		String sql = "SELECT * FROM t_student WHERE id = ?";
		Student stu = JdbcTemplate.queryByGeneric(sql, new BeanResultSetHandler<Student>(Student.class), id);
		return stu;
	}
}
