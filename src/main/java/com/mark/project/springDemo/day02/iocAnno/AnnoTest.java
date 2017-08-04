package com.mark.project.springDemo.day02.iocAnno;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * 通过注解的方式来配置达到和配置xml一样的效果
 *
 * 1. 标签
	 @Service用于标注业务层组件、
	 @Controller用于标注控制层组件（如struts中的action）、
	 @Repository用于标注数据访问组件，即DAO组件。
	 @Component泛指组件，当组件不好归类的时候，我们可以使用这个注解进行标注。
 *
 */
//表示某一种组件 效果等同于xml配置 <bean class="com.mark.project.springDemo.day02.iocAnno.AnnoTest" id="annoTert"></bean>
@Component
//表示bean的作用域
@Scope("prototype")
public class AnnoTest {


	//表示调用了构造器后执行该初始化方法 等同于xml配置中的init-method的配置
	@PostConstruct
	public void initMethod(){
		System.out.println("初始化");
	}

	public void doWork() {
		System.out.println("do work...");
	}

	//表示需要释放资源 等同于xml配置中的destroy-method的配置
	@PreDestroy
	public void destroyMethod() {
		System.out.println("销毁...");
	}


}
