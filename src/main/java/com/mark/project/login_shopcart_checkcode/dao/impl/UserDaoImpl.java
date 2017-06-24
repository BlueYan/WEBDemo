package com.mark.project.login_shopcart_checkcode.dao.impl;

import com.mark.project.handler.impl.BeanResultSetHandler;
import com.mark.project.login_shopcart_checkcode.dao.IUserDao;
import com.mark.project.login_shopcart_checkcode.domain.User;
import com.mark.project.util.JdbcTemplate;

/**
 * Created by Administrator on 2017/6/24.
 */
public class UserDaoImpl implements IUserDao {

	/**
	 * 用户登录
	 *
	 * @param username 用户名
	 * @param password 密码
	 * @return 存在即返回用户信息;不存在即返回null
	 */
	public User login(String username, String password) {
		String sql = "SELECT * FROM t_user WHERE username = ? AND password = ?";
		User user = JdbcTemplate.queryByGeneric(sql, new BeanResultSetHandler<User>(User.class), username, password);
		return user;
	}

}
