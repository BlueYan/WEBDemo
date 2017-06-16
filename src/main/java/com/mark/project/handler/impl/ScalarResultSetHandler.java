package com.mark.project.handler.impl;


import com.mark.project.handler.IResultSetHandler;

import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/5/31.
 */
public class ScalarResultSetHandler<T> implements IResultSetHandler<T> {

	private Class<T> clz;

	public ScalarResultSetHandler(Class<T> clz) {
		this.clz = clz;
	}

	public T handle(ResultSet rs) throws Exception {
		T obj = null;
		Class<?> type = clz; //将传递进来的类型先赋值给一个变量
		if ( rs.next() ) {
			Object value = rs.getObject(1); //因为查询出来的是单行单列的结果集 所以用下标1来取
			//判断是满足八种类型中的哪一种
			if ( clz == Long.class ) {
				type = long.class;
			} else if ( clz == Integer.class ) {
				type = int.class;
			} else if ( clz == Boolean.class ) {
				type = boolean.class;
			} else if ( clz == Short.class) {
				type = short.class;
			} else if ( clz == Float.class ) {
				type = float.class;
			} else if ( clz == Double.class ) {
				type = double.class;
			} else if ( clz == Byte.class ) {
				type = byte.class;
			} else {
				type = clz; //String类型
			}
			obj = clz.getConstructor(type).newInstance(value); //使用构造器传入一个类型参数然后构造出一个有value值的对象
		}
		return obj;
	}
}
