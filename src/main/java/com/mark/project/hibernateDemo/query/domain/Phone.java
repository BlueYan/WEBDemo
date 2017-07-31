package com.mark.project.hibernateDemo.query.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Mark_Yan on 2017/7/27.
 * 电话实体类

	 CREATE TABLE `t_phone` (
	 `id` bigint(20) NOT NULL AUTO_INCREMENT,
	 `number` varchar(255) DEFAULT NULL,
	 `employee_id` bigint(20) DEFAULT NULL,
	 `type` varchar(255) DEFAULT NULL,
	 PRIMARY KEY (`id`),
	 UNIQUE KEY `t_phone_id_uindex` (`id`),
	 KEY `employee_id` (`employee_id`),
	 CONSTRAINT `employee_id` FOREIGN KEY (`employee_id`) REFERENCES `t_employee` (`id`)
	 ) ENGINE=InnoDB DEFAULT CHARSET=utf8
 */
@Setter
@Getter
public class Phone {

	private Long id;

	private String number;

	private String type;

	private Employee employee; //对应有个外键是employee_id
}
