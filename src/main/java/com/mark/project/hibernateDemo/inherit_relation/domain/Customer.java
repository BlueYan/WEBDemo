package com.mark.project.hibernateDemo.inherit_relation.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Created by Mark_Yan on 2017/7/26.
 * 客户实体类继承用户实体类
 */
@Setter
@Getter
public class Customer extends User {

	private BigDecimal salary;

}
