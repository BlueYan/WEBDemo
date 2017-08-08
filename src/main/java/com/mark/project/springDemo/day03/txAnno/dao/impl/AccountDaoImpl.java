package com.mark.project.springDemo.day03.txAnno.dao.impl;

import com.mark.project.springDemo.day03.txAnno.dao.IAccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by Mark_Yan on 2017/8/8.
 */
@Repository("accountDao")
public class AccountDaoImpl implements IAccountDao {

	private JdbcTemplate mJdbc;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.mJdbc = new JdbcTemplate(dataSource);
	}

	public void transIn(Long inId, int balance) {
		mJdbc.update("UPDATE t_account SET balance = balance + ? WHERE id = ?", balance, inId);
	}

	public void transOut(Long outId, int balance) {
		mJdbc.update("UPDATE t_account SET balance = balance - ? WHERE id = ?", balance, outId);
	}
}
