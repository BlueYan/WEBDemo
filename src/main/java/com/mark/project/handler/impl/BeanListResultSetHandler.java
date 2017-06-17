package com.mark.project.handler.impl;


import com.mark.project.handler.IResultSetHandler;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/31.
 */
public class BeanListResultSetHandler<T> implements IResultSetHandler<List<T>> {

	private Class<T> clz;

	public BeanListResultSetHandler(Class<T> clz) {
		this.clz = clz;
	}

	public List<T> handle(ResultSet rs) throws Exception {
		List list = new ArrayList();
		PropertyDescriptor[] pds = Introspector.getBeanInfo(clz, Object.class).getPropertyDescriptors();//获取clz类中的所有属性描述
		while (rs.next() ) {
			T obj = clz.newInstance(); //利用反射获取一个实例
			for( PropertyDescriptor pd : pds ) {
				String name = pd.getName(); //获取属性名字
				Object value = rs.getObject(name);
				pd.getWriteMethod().invoke(obj, value); //再把值写入到obj属性中
			}
			list.add(obj);
		}
		return list;
	}
}
