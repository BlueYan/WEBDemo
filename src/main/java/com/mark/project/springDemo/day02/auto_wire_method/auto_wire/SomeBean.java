package com.mark.project.springDemo.day02.auto_wire_method.auto_wire;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Mark_Yan on 2017/8/4.
 */
@ToString
public class SomeBean {
	/**
	 * 添加Autowired注解可以自动注入属性 在SomeBean中并没有直接提供setter方法
	 * 1. 修改当前属性的名字 配置文件id="otherBean" 还是可以找到
	 * 2. 修改配置文件id="otherBean" 还是可以找到类
	 * 3. 配置文件增加多一个OtherBean节点,id取不同的名字 会报
	 *    No qualifying bean of type [com.mark.project.springDemo.day02.auto_wire_method.auto_wire.OtherBean1] is defined:
	 *    expected single matching bean but found 2: otherBean11,otherBean12
	 *    找到两个相同的id名字
	 */
	@Autowired
	OtherBean1 otherBean;
	@Autowired
	OtherBean2 otherBean2;

}
