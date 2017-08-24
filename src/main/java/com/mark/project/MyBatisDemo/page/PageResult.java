package com.mark.project.MyBatisDemo.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {

	private Long count;

	private List<T> data;
}
