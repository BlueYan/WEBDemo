package com.mark.project.smis.web.servlet;

import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.smis.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 * 定义学生列表Servlet
 */
@WebServlet("/Student/list")
public class StudentListServlet extends HttpServlet {

	private IStudentDao stuDao = null;

	@Override
	public void init() throws ServletException {
		stuDao = new StudentDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询所有学生的信息
		List<Student> stuList = stuDao.list();
		//将数据保存在作用域中
		req.setAttribute("stuList", stuList);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuList.jsp").forward(req, resp);
	}
}
