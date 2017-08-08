package com.mark.project.springDemo.day03.tx.dao;

/**
 * Created by Mark_Yan on 2017/8/8.
 *
 * 转账功能
 *
 */
public interface IAccountDao {
	//转入
	void transIn(Long inId, int balance);
	//转出
	void transOut(Long outId, int balance);
}
