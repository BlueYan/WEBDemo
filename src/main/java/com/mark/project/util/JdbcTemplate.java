package com.mark.project.util;


import com.mark.project.handler.IResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/5/31.
 *
 * 重构设计JDBC
 * 设计DML/DDL/DQL模板
 *
 */
public class JdbcTemplate {

	private JdbcTemplate() {}

	/**
	 * 该方法针对DDL以及DML的操作
	 * 对于sql的操作跟平常的一样的.获取连接,执行sql语句,关闭连接
	 * @param sql sql语句
	 * @param args sql语句中的占位符参数
	 * @return
	 * Example:
	 * 	sql = "INSERT INTO product(productName, dir_id, salePrice, supplier, brand, cutoff, costPrice) values(?, ?, ?, ?, ?, ?, ?)"
	 * 	args = 以上sql语句中的占位符实际的值 "罗技鼠标", 3, 99, "罗技", "罗技", 0.5, 50
	 */
	public static int update(String sql, Object ...args){
		Connection conn = null;
		PreparedStatement ps = null;
		int row = 0;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			//注意: 这里有多个参数 args传递进来的就是一个数组 所以使用循环
			//将需要的参数值全部由preparedStatement进行设置
			for ( int i = 0; i < args.length; i++ ) {
				ps.setObject(i + 1, args[i]);
			}
			row = ps.executeUpdate();
		} catch (Exception e ) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, null);
		}
		return row;
	}

	/**
	 * 对DQL进行重构设计
	 * 此时的返回值是list.也就是只能返回一个model类型的结果集但是却无法返回例如聚合函数查询的那种单个值的结果.
	 * 所以我们需要使用泛型来重新设计
	 * @param sql sql语句
	 * @param rsh 处理结果集的接口
	 * @param args 占位符值参数
	 * @return
	 */
	/*public static List queryOnlyList(String sql, IResultSetHandler rsh, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List list = new ArrayList();
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			//注意: 这里有多个参数 args传递进来的就是一个数组 所以使用循环
			//将需要的参数值全部由preparedStatement进行设置
			for ( int i = 0; i < args.length; i++ ) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			*//**
			 * 接下来是处理结果集
			 * 由于处理结果集的操作是无法统一的.毕竟每张表都有不同的字段或者我们查询的结果未必是一个结果集是一个聚合函数的值等
			 * 所以具体操作要由程序员自己来实现.
			 *//*
			//list = rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return list;
	}*/

	/**
	 * 带有泛型的重构设计模版
	 * @param sql sql语句
	 * @param rsh 处理结果集的接口 使用多态的性质来编程
	 * @param args 占位符值参数
	 * @param <T> 泛型
	 * @return
	 */
	public static <T> T queryByGeneric(String sql, IResultSetHandler<T> rsh, Object ...args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		T obj = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			//注意: 这里有多个参数 args传递进来的就是一个数组 所以使用循环
			//将需要的参数值全部由preparedStatement进行设置
			for ( int i = 0; i < args.length; i++ ) {
				ps.setObject(i + 1, args[i]);
			}
			rs = ps.executeQuery();
			/**
			 * 接下来是处理结果集
			 * 由于处理结果集的操作是无法统一的.毕竟每张表都有不同的字段或者我们查询的结果未必是一个结果集是一个聚合函数的值等
			 * 所以具体操作要由程序员自己来实现.
			 */
			obj = rsh.handle(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, ps, rs);
		}
		return obj;
	}


}
