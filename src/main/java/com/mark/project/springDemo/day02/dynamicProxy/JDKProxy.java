package com.mark.project.springDemo.day02.dynamicProxy;

import com.mark.project.util.TransactionManager;
import lombok.Setter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * JDK动态代理类
 *
 */
public class JDKProxy implements InvocationHandler{
	//将真实对象设置成Object类型是因为 由于有多种Service 我们不可能单单写一种 所以用Object来接收
	@Setter
	private Object obj;

	@Setter
	private TransactionManager txManager;

	/**
	 * 获取代理对象
	 * @param <T>
	 * @return
	 */
	public <T> T getObject() {
		return (T) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	/**
	 * 执行真实对象的接口方法
	 * @param proxy
	 * @param method
	 * @param args
	 * @return
	 * @throws Throwable
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object rs = null;
		try {
			txManager.openTransaction();
			rs = method.invoke(obj, args);
			txManager.closeTransaction();
		} catch (Exception e) {
			txManager.rollbackTransaction();
		}
		return rs;
	}
}
