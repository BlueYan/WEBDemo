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
 * 由于在web-inf文件夹下的资源只能通过请求跳转访问 所以该servlet作用就是用来跳转
 */
@WebServlet("/Student/edit")
public class StudentEditServlet extends HttpServlet {

	private IStudentDao stuDao = null;

	@Override
	public void init() throws ServletException {
		if ( stuDao == null ) {
			stuDao = new StudentDaoImpl();
		}
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Student stu = stuDao.get(Integer.valueOf(id));
			//将数据保存到作用域中
			req.setAttribute("stu", stu);
		}
		req.getRequestDispatcher("/WEB-INF/smis/views/stuAdd.jsp").forward(req, resp);
	}
}
