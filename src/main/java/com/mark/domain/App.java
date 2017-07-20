package com.mark.domain;

import java.lang.reflect.Constructor;

/**
 * Created by Mark_Yan on 2017/7/18.
 */
public class App {

	public static void main(String[] args) throws Exception {
		Class<User> clz = User.class;
		Constructor<User> con = clz.getConstructor(); //获取无参数
		User user = con.newInstance(); //调用无参数构造器创建User对象
	}

}
