package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/25.
 */
public interface StudentMapper {

	void save(Student student);

	Student get(Long id);

	void updateForStuId(@Param("stuID") Long stuID, @Param("teacherID") Long teach_id);

	List<Student> select();

	void updateRelation(Long stuID);

	void delete(Long id);
}
