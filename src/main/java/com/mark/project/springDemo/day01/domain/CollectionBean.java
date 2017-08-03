package com.mark.project.springDemo.day01.domain;

import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Mark_Yan on 2017/8/3.
 *
 * 集合属性
 *
 */
@Setter
@ToString
public class CollectionBean {

	private Set<String> set;

	private List<String> list;

	private Map<String, String> map;

	private String[] array;

	private Properties prop;

}
