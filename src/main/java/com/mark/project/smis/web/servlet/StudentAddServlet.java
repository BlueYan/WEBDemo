package com.mark.project.smis.web.servlet;

import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.smis.domain.Student;
import com.mark.project.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/16.
 * 添加学生信息Servlet
 */
@WebServlet("/Student/add")
public class StudentAddServlet extends HttpServlet {

	private IStudentDao stuDao = null;

	@Override
	public void init() throws ServletException {
		if ( stuDao == null ) {
			stuDao = new StudentDaoImpl();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8"); //解决中文乱码问题 只适用于post请求
		String name = req.getParameter("name");
		String age = req.getParameter("age");
		String id = req.getParameter("id"); //获取用户id
		System.out.println("name = " + name +" age = " + age);
		if ( StringUtil.isNotEmpty(name) && StringUtil.isNotEmpty(age) ) {
			Student student = new Student(0, name, Integer.parseInt(age));
			if ( StringUtil.isNotEmpty(id) ) {
				//不为空 表示更新用户
				student.setId(Integer.parseInt(id));
				stuDao.update(student);
			} else {
				stuDao.add(student);
			}
		}
		//跳转到列表页面
		resp.sendRedirect("/Student/list");
	}
}
