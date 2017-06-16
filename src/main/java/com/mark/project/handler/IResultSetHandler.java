package com.mark.project.handler;

import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/5/31.
 *
 * 处理结果集的接口
 * 由于每个查询出来的结果集都是不一样的,我们可能需要的列或者表都是无法同一.
 * 所以这个操作应该由程序员自己来具体实现.
 * 规范:
 * 		定义一个处理结果集的方法.程序员需要实现该接口中的方法.
 *
 */
public interface IResultSetHandler<T> {

	/**
	 * T表示泛型 返回任何类型
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	T handle(ResultSet rs) throws Exception;

}
