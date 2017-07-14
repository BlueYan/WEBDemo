package com.mark.project.employeeMS.web.action;

import com.mark.project.employeeMS.dao.IEmployeeDao;
import com.mark.project.employeeMS.dao.impl.EmployeeDaoImpl;
import com.mark.project.employeeMS.domain.Employee;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public class EmployeeAction extends ActionSupport {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	public String list() {
		//获取所有的员工信息
		List<Employee> employees = employeeDao.list();
		//保存员工信息到valueStack
		ActionContext.getContext().put("employees", employees);
		return SUCCESS;
	}


}
