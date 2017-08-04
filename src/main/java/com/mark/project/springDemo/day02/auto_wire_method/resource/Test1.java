package com.mark.project.springDemo.day02.auto_wire_method.resource;


import lombok.ToString;

import javax.annotation.Resource;

/**
 * Created by Mark_Yan on 2017/8/4.
 */
public class Test1 {
	/**
	 * Resource标签可以用于注入一些内置的对象
	 *
	 * 1. 修改当前属性的命名名称.配置文件不作任何修改. 可以找到
	 *
	 */
	@Resource
	Test2 test21;

}
