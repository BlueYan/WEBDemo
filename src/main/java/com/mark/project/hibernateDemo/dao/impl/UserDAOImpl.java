package com.mark.project.hibernateDemo.dao.impl;

import com.mark.project.hibernateDemo.dao.IUserDAO;
import com.mark.project.hibernateDemo.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/18.
 */
public class UserDAOImpl implements IUserDAO {
	public void save(User u) {
		//1. 创建配置对象
		Configuration config = new Configuration();
		//2. 读取配置文件
		config.configure(); //源码默认会读取hibernate.cfg.xml文件
		//3. 创建SessionFactory对象 类似与连接池
		SessionFactory factory = config.buildSessionFactory();
		//4. 创建session对象 用于实现crud操作
		Session session = factory.openSession();
		//5. 具体逻辑操作
		//5.1 开启事务
		session.beginTransaction();
		session.save(u); //增加
		//5.2 提交事务
		session.getTransaction().commit();
		//6. 关闭session连接
		session.close();
		//7. 关闭SessionFactory连接
		factory.close();
	}

	public void delete(Long id) {
		//1. 创建配置对象
		Configuration config = new Configuration();
		//2. 读取配置文件
		config.configure(); //源码默认会读取hibernate.cfg.xml文件
		//3. 创建SessionFactory对象 类似与连接池
		SessionFactory factory = config.buildSessionFactory();
		//4. 创建session对象 用于实现crud操作
		Session session = factory.openSession();
		//5. 具体逻辑操作
		//5.1 开启事务
		session.beginTransaction();
		User user = new User(); //需要传入对象 因为要根据对象的字节码找到映射文件中的table这样才能知道要删除的是哪一张表
		user.setId(id);
		session.delete(user); //更新
		//5.2 提交事务
		session.getTransaction().commit();
		//6. 关闭session连接
		session.close();
		//7. 关闭SessionFactory连接
		factory.close();
	}

	public void update(User user) {
		//1. 创建配置对象
		Configuration config = new Configuration();
		//2. 读取配置文件
		config.configure(); //源码默认会读取hibernate.cfg.xml文件
		//3. 创建SessionFactory对象 类似与连接池
		SessionFactory factory = config.buildSessionFactory();
		//4. 创建session对象 用于实现crud操作
		Session session = factory.openSession();
		//5. 具体逻辑操作
		//5.1 开启事务
		session.beginTransaction();
		session.update(user); //更新
		//5.2 提交事务
		session.getTransaction().commit();
		//6. 关闭session连接
		session.close();
		//7. 关闭SessionFactory连接
		factory.close();
	}

	public User get(Long id) {
		//1. 创建配置对象
		Configuration config = new Configuration();
		//2. 读取配置文件
		config.configure(); //源码默认会读取hibernate.cfg.xml文件
		//3. 创建SessionFactory对象 类似与连接池
		SessionFactory factory = config.buildSessionFactory();
		//4. 创建session对象 用于实现crud操作
		Session session = factory.openSession();
		//5. 具体逻辑操作 获取单个
		//传入User.class主要是两个目的 第一告诉对应的表 可以从映射文件找到 第二是查询后需要将结果转成哪一种实体
		User user = (User) session.get(User.class, id);
		//6. 关闭session连接
		session.close();
		//7. 关闭SessionFactory连接
		factory.close();
		return user;
	}

	public List<User> list() {
		//1. 创建配置对象
		Configuration config = new Configuration();
		//2. 读取配置文件
		config.configure(); //源码默认会读取hibernate.cfg.xml文件
		//3. 创建SessionFactory对象 类似与连接池
		SessionFactory factory = config.buildSessionFactory();
		//4. 创建session对象 用于实现crud操作
		Session session = factory.openSession();
		//5. 具体逻辑操作 获取多个
		//5.1 创建hql语句
		//相当于sql语句: SELECT * FROM t_user_new --> SELECT u FROM User u
		String hql = "SELECT u FROM User u"; //User是对应的实体类也是能找到对应的表 u表示别名 前后别名要一致
		//5.2 创建query对象
		Query query = session.createQuery(hql);
		//5.3 真正执行查询
		List<User> users = query.list();
		//6. 关闭session连接
		session.close();
		//7. 关闭SessionFactory连接
		factory.close();
		return users;
	}
}
