package com.mark.project.MyBatisDemo.service;

import com.mark.project.MyBatisDemo.domain.Account;
import com.mark.project.MyBatisDemo.page.PageResult;
import com.mark.project.MyBatisDemo.query.QueryObject;

/**
 * Created by Mark_Yan on 2017/8/24.
 */
public interface IAccountService {

	PageResult<Account> pageQuery(QueryObject qo);

}
