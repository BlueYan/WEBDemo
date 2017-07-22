package com.mark.project.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Mark_Yan on 2017/7/19.
 */
public class HibernateUtil {

	private static SessionFactory sf = null;

	private HibernateUtil() {}

	static {
		//静态代码块 只会执行一次
		Configuration cfg = new Configuration().configure();
		sf = cfg.buildSessionFactory(); //创建SessionFactory对象
	}

	public static Session getSession() {
		return sf.openSession(); //测试时候用该方法

		//return sf.getCurrentSession(); //开发的时候用该方法
	}


}
