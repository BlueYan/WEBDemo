package com.mark.project.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * Created by Mark_Yan on 2017/8/22.
 */
public class MyBatisUtil {

	private MyBatisUtil() {}

	private static SqlSessionFactory sf = null;

	static {
		try {
			sf = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis/mybatis.cfg.xml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static SqlSession getSession() {
		return sf.openSession();
	}

}
