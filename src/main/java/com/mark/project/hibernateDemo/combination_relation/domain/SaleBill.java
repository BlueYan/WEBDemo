package com.mark.project.hibernateDemo.combination_relation.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/7/25.
 * 单据实体类
 * 一个单据拥有多个明细
 *
 */
@Setter
@Getter
public class SaleBill {

	private Long id;

	private Date saleDate;

	private List<SaleBillItem> billItems = new ArrayList<SaleBillItem>();

}
