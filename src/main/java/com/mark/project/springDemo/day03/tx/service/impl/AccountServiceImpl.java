package com.mark.project.springDemo.day03.tx.service.impl;

import com.mark.project.springDemo.day03.tx.dao.IAccountDao;
import com.mark.project.springDemo.day03.tx.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Mark_Yan on 2017/8/8.
 */
@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDaoImpl;

	public void trans(Long outId, Long inId, int money) {
		accountDaoImpl.transOut(outId, money);
		int i = 1 / 0;
		accountDaoImpl.transIn(inId, money);
	}
}
