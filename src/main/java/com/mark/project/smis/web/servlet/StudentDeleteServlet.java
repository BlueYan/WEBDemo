package com.mark.project.smis.web.servlet;

import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/6/16.
 */
@WebServlet("/Student/delete")
public class StudentDeleteServlet extends HttpServlet {

	private IStudentDao stuDao = null;

	@Override
	public void init() throws ServletException {
		stuDao = new StudentDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//获取参数ID
		String id = req.getParameter("id");
		if ( StringUtil.isNotEmpty(id) ) {
			stuDao.delete(Integer.valueOf(id));
		}
		//进行跳转
		resp.sendRedirect("/Student/list");
	}
}
