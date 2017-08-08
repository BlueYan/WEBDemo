package com.mark.project.springDemo.day02.aopTest;

import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by Mark_Yan on 2017/8/7.
 * 事务管理
 * 增强操作 需要从整个类中进行切入
 * 将需要增加的操作切入到对应的Service层中
 */
@Component
//表示支持aop切入
@Aspect
public class TranManager {

	/**
	 * 定义一个方法 用来表示切入点的名字
	 * <aop:pointcut id="servicePointCut" expression="execution(* com.mark.project.springDemo.day02.aopTest.service.*Service.*(..))"/>
	 * Pointcut注解的作用: 相当于在xml文件中配置表达式 而方法的名字就是相当于id
	 */
	@Pointcut("execution(* com.mark.project.springDemo.day02.aopTest.service.*Service.*(..))")
	public void servicePointCut() {}

	/**
	 * 开始事务操作 是在切入点之前做的
	 * 所以要使用Before注解
	 *  <aop:before method="openTransaction" pointcut-ref="servicePointCut"/>
	 */
	@Before("servicePointCut()")
	public void openTransaction() {
		System.out.println("开启事务");
	}

	/**
	 * 关闭事务操作 是在return之前做的
	 * <aop:after-returning method="closeTransaction" pointcut-ref="servicePointCut"/>
	 */
	@AfterReturning("servicePointCut()")
	public void closeTransaction() {
		System.out.println("关闭事务");
	}

	/**
	 * 当程序出现错误异常的时候,事务需要进行回滚
	 * <aop:after-throwing method="rollbackTransaction" pointcut-ref="servicePointCut"/>
	 */
	@AfterThrowing("servicePointCut()")
	public void rollbackTransaction() {
		System.out.println("事务回滚");
	}

	/**
	 * 关闭资源在finally里完成
	 * <aop:after method="closeResource" pointcut-ref="servicePointCut"/>
	 */
	@After("servicePointCut()")
	public void closeResource() {
		System.out.println("关闭资源");
	}
}
