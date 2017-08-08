package com.mark.project.springDemo.day03.txAnno.service;

/**
 * Created by Mark_Yan on 2017/8/8.
 */
public interface IAccountService {

	void trans(Long outId, Long inId, int money);

}
