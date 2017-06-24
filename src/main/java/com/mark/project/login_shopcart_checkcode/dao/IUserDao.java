package com.mark.project.login_shopcart_checkcode.dao;


import com.mark.project.login_shopcart_checkcode.domain.User;

/**
 * Created by Administrator on 2017/6/24.
 */
public interface IUserDao {

	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码
	 * @return 存在即返回用户信息;不存在即返回null
	 */
	User login(String username, String password);
}
