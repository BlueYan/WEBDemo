package com.mark.project.smis.dao.impl;

import com.mark.project.handler.impl.BeanListResultSetHandler;
import com.mark.project.handler.impl.BeanResultSetHandler;
import com.mark.project.handler.impl.ScalarResultSetHandler;
import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.domain.Student;
import com.mark.project.smis.page.PageResult;
import com.mark.project.smis.query.StudentQueryObject;
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

	/**
	 * 这里对于整型数字的参数使用的是包装类型的Integer而不是用基本数据类型。
	 * 原因是因为: 如果用基本数据类型的话,默认值是0。那就会造成不知道是用户输入的是0还是没输入。而使用Integer类型可以用null来判断。
	 * @param name 需要查询的名字
	 * @param minAge 最小年龄
	 * @param maxAge 最大年龄
     * @return
     */
	public List<Student> query(String name, Integer minAge, Integer maxAge) {
		StringBuilder sb  = new StringBuilder("SELECT * FROM t_student ");
		StudentQueryObject sqo = new StudentQueryObject();
		sqo.setName(name);
		sqo.setMinAge(minAge);
		sqo.setMaxAge(maxAge);
		sb.append(sqo.query());
		//param需要转成数组 因为该方法接受的参数是一个可变参数本质上就是数组
		return JdbcTemplate.queryByGeneric(sb.toString(), new BeanListResultSetHandler<Student>(Student.class),
				sqo.getParams().toArray());
	}

	/**
	 * 分页查询
	 *
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	public PageResult<Student> page(Integer currentPage, Integer pageSize) {
		String sql = "SELECT * FROM t_student LIMIT ?, ?";
		List<Student> stuList = JdbcTemplate.queryByGeneric(sql, new BeanListResultSetHandler<Student>(Student.class),
				(currentPage - 1)*pageSize, pageSize);
		sql = "SELECT COUNT(*) FROM t_student";
		Long totalCount = JdbcTemplate.queryByGeneric(sql, new ScalarResultSetHandler<Long>(Long.class));
		PageResult<Student> stuPageResult = new PageResult<Student>(stuList, totalCount.intValue(), currentPage, pageSize);
		return stuPageResult;
	}

	/**
	 * 分页高级查询
	 * 1. 查询带有过滤的分页数据
	 * 	  SELECT * FROM t_student + (此处应该加上过滤的参数 where xxxx。然后这些参数都是存放在stuqo对象中的。sql语句后面的where条件
	 * 	  都是由该类去拼接。所以只要调用获取拼接的sql字符串就可以了) + LIMIT ?, ?
	 *
	 * @param stuqo 查询的参数列表对象 在servlet中封装好后 传递过来
	 * @return
	 */
	public PageResult<Student> pageQuery(StudentQueryObject stuqo) {
		String condition = stuqo.query(); //单独调用一次。然后调用多次会造成条件重复
		String sql = "SELECT COUNT(*) FROM t_student " + condition;
		System.out.println("sql = " + sql);
		Integer totalPage = JdbcTemplate.queryByGeneric(sql, new ScalarResultSetHandler<Long>(Long.class), stuqo.getParams().toArray()).intValue();
		sql = "SELECT * FROM t_student " + condition + " LIMIT ?, ?";
		//这里之所以要添加这两个参数。是因为我们从servlet传递过来的stuqo对象中handleParams方法并没有把currentPage和pageSize参数添加到params集合中
		stuqo.getParams().add((stuqo.getCurrentPage() - 1) * stuqo.getPageSize());
		stuqo.getParams().add(stuqo.getPageSize());
		List<Student> students = JdbcTemplate.queryByGeneric(sql,
				new BeanListResultSetHandler<Student>(Student.class), stuqo.getParams().toArray());//这里要传递参数进去.
		PageResult<Student> stuResult = new PageResult<Student>(students, totalPage, stuqo.getCurrentPage(), stuqo.getPageSize());
		return stuResult;
	}
}
