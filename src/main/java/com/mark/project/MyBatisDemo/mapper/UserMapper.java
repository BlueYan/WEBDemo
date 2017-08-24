package com.mark.project.MyBatisDemo.mapper;

import com.mark.project.MyBatisDemo.domain.Account;
import com.mark.project.MyBatisDemo.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

	List<Account> queryByCondition(QueryObject qo);

	void updateByCondition(Account account);

	List<Account> queryForIn(List<Long> ids);

	Long queryForCount(QueryObject qo);

	List<Account> queryForPage(QueryObject qo);


	/**
	 * 对应的Mapper接口定义该方法
	 * @param map map参数
	 * @return
	 */
	Account loginByMap(Map map);

	/**
	 * 传递多个参数
	 * 贴上@Param标签用来表示对应的参数。
	 * @param username
	 * @param password
	 * @return
	 */
	Account login(@Param("username")String username, @Param("password") String password);

}
