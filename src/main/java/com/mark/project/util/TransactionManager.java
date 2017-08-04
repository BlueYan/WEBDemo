package com.mark.project.util;

/**
 * Created by Mark_Yan on 2017/8/4.
 *
 * 事务操作管理 模拟事务的开启和关闭
 *
 */
public class TransactionManager {

	public void openTransaction() {
		System.out.println("开启事务");
	}

	public void closeTransaction() {
		System.out.println("关闭事务");
	}

	public void rollbackTransaction() {
		System.out.println("事务回滚");
	}

}
