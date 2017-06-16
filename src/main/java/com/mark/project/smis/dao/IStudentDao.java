package com.mark.project.smis.dao;

import com.mark.project.smis.domain.Student;

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

}
