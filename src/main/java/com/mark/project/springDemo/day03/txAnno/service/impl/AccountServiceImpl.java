package com.mark.project.springDemo.day03.txAnno.service.impl;

import com.mark.project.springDemo.day03.txAnno.dao.IAccountDao;
import com.mark.project.springDemo.day03.txAnno.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Mark_Yan on 2017/8/8.
 */
@Service("accountService")
@Transactional //为service中的所有方法添加事务的操作.如果想针对某个方法设置不需要事务 则在方法上贴@Transactional(readOnly=true)
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	public void trans(Long outId, Long inId, int money) {
		accountDao.transOut(outId, money);
		//int i = 1 / 0;
		accountDao.transIn(inId, money);
	}
}
