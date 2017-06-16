package com.mark.project.util;

/**
 * Created by Administrator on 2017/6/16.
 */
public class StringUtil {

	private StringUtil() {}

	public static boolean isNotEmpty(String str) {
		return str != null && !"".equals(str);
	}

}
