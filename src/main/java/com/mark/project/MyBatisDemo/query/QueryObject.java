package com.mark.project.MyBatisDemo.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
@Setter
@Getter
@ToString
public class QueryObject {


	private String keyword;

	private Integer beginAge;

	private Integer endAge;

	private Integer currentPage;

	private Integer pageSize;

	public Integer getStartPage() {
		return (currentPage - 1) * pageSize;
	}
}
