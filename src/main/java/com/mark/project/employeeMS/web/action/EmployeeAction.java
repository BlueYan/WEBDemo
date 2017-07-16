package com.mark.project.employeeMS.web.action;

import com.mark.project.employeeMS.dao.IEmployeeDao;
import com.mark.project.employeeMS.dao.impl.EmployeeDaoImpl;
import com.mark.project.employeeMS.domain.Employee;
import com.mark.project.employeeMS.page.PageResult;
import com.mark.project.employeeMS.query.EmployeeQuery;
import com.mark.project.util.CommonUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import lombok.Getter;
import lombok.Setter;
import sun.jvm.hotspot.debugger.Page;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public class EmployeeAction extends ActionSupport {

	private IEmployeeDao employeeDao = new EmployeeDaoImpl();

	// 声明employee对象 并且提供get和set方法。用来封装前台传递进来的参数
	@Getter
	@Setter
	private Employee e;

	@Setter
	@Getter
	EmployeeQuery eqo = new EmployeeQuery();

	/**
	 * 显示列表
	 * @return
     */
	public String list() {
		//获取所有的员工信息
		// List<Employee> employees = employeeDao.query(eqo);
		//带有分页和高级查询的列表
		PageResult<Employee> pageResult = employeeDao.pageQuery(eqo);
		//保存员工信息到valueStack
		ActionContext.getContext().put("employeesPR", pageResult);
		return SUCCESS;
	}

	/**
	 * 删除
	 * @return
     */
	public String delete() {
		System.out.println(e);
		if ( e.getId() != null ) {
			employeeDao.delete(e.getId());
		}
		return "list";
	}

	/**
	 * 新增或者更新
	 * @return
     */
	public String saveOrUpdate() {
		System.out.println("saveOrUpdate = " + e);
		if ( e.getId() != null ) {
			employeeDao.update(e);
		} else {
			employeeDao.save(e);
		}
		return "list";
	}

	/**
	 * 提供一个input方法
	 * 主要是为了前台通过a标签跳转到其他页面。由于其他页面在web-inf文件夹下,无法直接访问,只能通过action来跳转
	 * 并且当发生校验错误时候需要跳转到input中
	 * @return
	 * @throws Exception
     */
	public String input() throws Exception {
		if ( e.getId() != null ) {
			e = employeeDao.get(e.getId()); //获取当前员工信息。用于编辑页面显示。e对象存放在valueStack的root区域中
			System.out.println(e);
		}
		return INPUT;
	}

	/**
	 * 校验数据
	 */
	public void validateSaveOrUpdate() {
		System.out.println("validate...");
		if ( !CommonUtil.isNotEmpty(e.getEmpName()) || e.getEmpName().length() > 6 ) {
			this.addFieldError("e.empName", "账号名字不能为空并且长度不能超过六位");
		}
		if ( !CommonUtil.isNotEmpty(e.getEmail()) || !"^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$".matches(e.getEmail()) ) {
			this.addFieldError("e.email", "邮箱不能为空并且格式如右: xxx@xx.com");
		}
	}
}
