package com.mark.project.hibernateDemo.dao;

import com.mark.project.hibernateDemo.domain.User;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/18.
 */
public interface IUserDAO {

	void save(User u);

	void delete(Long id);

	void update(User user);

	User get(Long id);

	List<User> list();

}
