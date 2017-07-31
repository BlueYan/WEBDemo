package com.mark.project.hibernateDemo.query.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/7/26.
 *
 * 一个部门可以拥有多个员工
 * 一个员工只能在一个部门
 * 部门 ：员工 = 1 ：n
 * one2many
	 CREATE TABLE `t_dept` (
	 `id` bigint(20) NOT NULL AUTO_INCREMENT,
	 `name` varchar(255) DEFAULT NULL,
	 `sn` varchar(255) DEFAULT NULL,
	 `provice` varchar(255) DEFAULT NULL,
	 `city` varchar(255) DEFAULT NULL,
	 `street` varchar(255) DEFAULT NULL,
	 `MANAGER_ID` bigint(20) DEFAULT NULL,
	 PRIMARY KEY (`id`),
	 UNIQUE KEY `t_dept_id_uindex` (`id`),
	 KEY `manager_id` (`MANAGER_ID`),
	 CONSTRAINT `manager_id` FOREIGN KEY (`MANAGER_ID`) REFERENCES `t_employee` (`id`)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8

 */
@Getter
@Setter
public class Department {

	private Long id;

	private String name;

	private String sn;

	private String province;

	private String city;

	private String street;

	/**
	 * 从以上创建表的结构来说，MANAGER_ID是t_employee表的主键作为t_dept表中MANAGER_ID的外键
	 * manager_id是用来存储employee表的id,标识该部门的经理是哪一位员工.
	 */
	private Employee manager;


}
