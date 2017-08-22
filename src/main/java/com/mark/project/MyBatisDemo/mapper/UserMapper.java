package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Account;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/22.
 *
 * 定义一个UserMapper接口
 * 定义所有的操作方法
 * 规范：
 * 		我们配置对应的Mapper.xml文件中的namespace必须是该接口的全限定名com.mark.project.MyBatisDemo.mapper.UserMapper
 * 		配置的id必须是该接口中的方法.
 */
public interface UserMapper {

	public void save(Account account);

	void update(Account account);

	void delete(Long id);

	Account get(Long id);

	List<Account> list();

}
