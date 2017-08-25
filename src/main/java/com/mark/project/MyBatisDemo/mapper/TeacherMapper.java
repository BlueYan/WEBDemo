package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Teacher;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Mark_Yan on 2017/8/25.
 */
public interface TeacherMapper {

	void save(Teacher teacher);

	Teacher get(Long id);
}
