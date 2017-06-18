package com.mark.project.smis.dao;

import com.mark.project.smis.domain.Student;
import com.mark.project.smis.page.PageResult;
import com.mark.project.smis.query.StudentQueryObject;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 * 定义学生DAO类
 */
public interface IStudentDao {

	/**
	 * 查询学生信息
	 * @return
	 */
	List<Student> list();

	/**
	 * 删除学生信息
	 * @param id
	 */
	void delete(int id);

	/**
	 * 添加学生
	 * @param stu
	 */
	void add(Student stu);

	/**
	 * 更新用户对象
	 * @param stu
	 */
	void update(Student stu);

	/**
	 * 根据id获取对象
	 * @param id
	 * @return
	 */
	Student get(int id);

	/**
	 * 根据多个条件查询
	 * @param name 需要查询的名字
	 * @param minAge 最小年龄
	 * @param maxAge 最大年龄
     * @return 返回查询的学生的集合
     */
	List<Student> query(String name, Integer minAge, Integer maxAge);

	/**
	 * 分页查询
	 * @param currentPage
	 * @param pageSize
     * @return
     */
	PageResult<Student> page(Integer currentPage, Integer pageSize);

	/**
	 * 分页高级查询
	 * @param stuqo 查询的参数列表对象 在servlet中封装好后 传递过来
	 * @return
     */
	PageResult<Student> pageQuery(StudentQueryObject stuqo);
}
