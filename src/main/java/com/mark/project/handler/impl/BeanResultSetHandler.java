package com.mark.project.handler.impl;


import com.mark.project.handler.IResultSetHandler;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/5/31.
 */
public class BeanResultSetHandler<T> implements IResultSetHandler<T> {
    //利用反射进行获取属性值
	private Class<T> clz;

	public BeanResultSetHandler(Class<T> clz) {
		this.clz = clz;
	}

	public T handle(ResultSet rs) throws Exception {
		T obj = null;
		if ( rs.next() ) {
			obj = clz.newInstance(); //利用反射获取一个实例
			PropertyDescriptor[] pds = Introspector.getBeanInfo(clz, Object.class).getPropertyDescriptors();//获取clz类中的所有属性描述
			for( PropertyDescriptor pd : pds ) {
				String name = pd.getName(); //获取属性名字
				Object value = rs.getObject(name);
				pd.getWriteMethod().invoke(obj, value); //再把值写入到obj属性中
			}
		}
		return obj;
	}
}
