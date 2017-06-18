package com.mark.project.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/5/31.
 * JDBC工具类
 */
public class JdbcUtil {

	private static String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
	private static String user = "root";
	private static String pwd = "123456";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws Exception {
		return DriverManager.getConnection(url, user, pwd);
	}

	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if ( rs != null ) {
				rs.close();
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
		try {
			if ( ps != null ) {
				ps.close();
			}
		} catch (Exception e ) {
			e.printStackTrace();
		}
		try {
			if ( conn != null ) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
