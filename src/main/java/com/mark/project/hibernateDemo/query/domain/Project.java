package com.mark.project.hibernateDemo.query.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/27.
 * 项目实体类

	 CREATE TABLE `t_project` (
	 `id` bigint(20) NOT NULL AUTO_INCREMENT,
	 `name` varchar(255) DEFAULT NULL,
	 `MANAGER_ID` bigint(20) DEFAULT NULL,
	 PRIMARY KEY (`id`),
	 UNIQUE KEY `t_project_id_uindex` (`id`),
	 KEY `t_project_employee` (`MANAGER_ID`),
	 CONSTRAINT `t_project_employee` FOREIGN KEY (`MANAGER_ID`) REFERENCES `t_employee` (`id`)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8

 	中间表:
	 CREATE TABLE `project_employee` (
	 `employee_id` bigint(20) NOT NULL,
	 `project_id` bigint(20) NOT NULL,
	 KEY `FK_fvrxocpfy4anjc4bxawptwecf` (`project_id`),
	 KEY `FK_5vqdjlqkbtf4gd25w8yke9yds` (`employee_id`),
	 CONSTRAINT `FK_5vqdjlqkbtf4gd25w8yke9yds` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`id`),
	 CONSTRAINT `FK_fvrxocpfy4anjc4bxawptwecf` FOREIGN KEY (`project_id`) REFERENCES `t_project` (`id`)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8


  一个项目可以用多个员工
  一个员工可以开发多个项目
  项目 : 员工 = n : n many2many的映射关系

 */
@Getter
@Setter
public class Project {

	private Long id;

	private String name;

	private Employee employee; //作为外键manager_id

	private List<Employee> employees = new ArrayList<Employee>();

}
