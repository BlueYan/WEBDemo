package com.mark.project.MyBatisDemo.service.impl;


import com.mark.project.MyBatisDemo.domain.Account;
import com.mark.project.MyBatisDemo.mapper.UserMapper;
import com.mark.project.MyBatisDemo.page.PageResult;
import com.mark.project.MyBatisDemo.query.QueryObject;
import com.mark.project.MyBatisDemo.service.IAccountService;
import com.mark.project.util.MyBatisUtil;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
public class AccountServiceImpl implements IAccountService {

	public PageResult<Account> pageQuery(QueryObject qo) {
		SqlSession sessions = MyBatisUtil.getSession();
		UserMapper userMapperDAO = sessions.getMapper(UserMapper.class);
		Long count = userMapperDAO.queryForCount(qo);
		if ( count == null ) {
			return new PageResult<Account>(0L, Collections.<Account>emptyList());
		}
		List<Account> accounts = userMapperDAO.queryForPage(qo);
		sessions.close();
		return new PageResult<Account>(count, accounts);
	}
}
