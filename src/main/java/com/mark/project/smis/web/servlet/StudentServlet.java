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
import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 * 合并学生类的CRUD操作Servlet
 */
@WebServlet("/Student")
public class StudentServlet extends HttpServlet {

	private IStudentDao stuDao = null;

	@Override
	public void init() throws ServletException {
		stuDao = new StudentDaoImpl();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String cmd = req.getParameter("cmd");
		if ( "delete".equals(cmd) ) {
			//执行delete操作
			delete(req, resp);
		} else if ( "saveOrUpdate".equals(cmd) ) {
			saveOrUpdate(req, resp);
		} else if ( "edit".equals(cmd) ) {
			edit(req, resp);
		} else {
			list(req, resp);
		}
	}

	/**
	 * 删除
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//获取参数ID
		String id = req.getParameter("id");
		if ( StringUtil.isNotEmpty(id) ) {
			stuDao.delete(Integer.valueOf(id));
		}
		//进行跳转 URL重定向
		resp.sendRedirect(req.getContextPath() + "/Student/list");
	}

	/**
	 * 添加或者更新
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void saveOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		resp.sendRedirect(req.getContextPath() + "/Student/list");
	}

	/**
	 * 编辑
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void edit(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		if (StringUtil.isNotEmpty(id)) {
			Student stu = stuDao.get(Integer.valueOf(id));
			//将数据保存到作用域中
			req.setAttribute("stu", stu);
		}
		req.getRequestDispatcher("/WEB-INF/smis/views/stuAdd.jsp").forward(req, resp);
	}

	/**
	 * 查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//查询所有学生的信息
		List<Student> stuList = stuDao.list();
		//将数据保存在作用域中
		req.setAttribute("stuList", stuList);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuList.jsp").forward(req, resp);
	}
}
