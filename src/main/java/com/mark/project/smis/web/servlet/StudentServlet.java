package com.mark.project.smis.web.servlet;

import com.mark.project.login_shopcart_checkcode.domain.User;
import com.mark.project.smis.dao.IStudentDao;
import com.mark.project.smis.dao.impl.StudentDaoImpl;
import com.mark.project.smis.domain.Student;
import com.mark.project.smis.page.PageResult;
import com.mark.project.smis.query.StudentQueryObject;
import com.mark.project.util.CommonUtil;

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
		} else if ( "query".equals(cmd) ) {
			query(req, resp);
		} else if ( "page".equals(cmd) ) {
			page(req, resp);
		} else if ( "pageQuery".equals(cmd) ) {
			pageQuery(req, resp);
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
		if ( CommonUtil.isNotEmpty(id) ) {
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
		if ( CommonUtil.isNotEmpty(name) && CommonUtil.isNotEmpty(age) ) {
			Student student = new Student(0, name, Integer.parseInt(age));
			if ( CommonUtil.isNotEmpty(id) ) {
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
		if (CommonUtil.isNotEmpty(id)) {
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
		User u = (User) req.getSession().getAttribute("USER_IN_SESSION");
		if ( u == null ) {
			//表示没有登录 跳转回登录页面
			resp.sendRedirect("/login_shopcart_checkcode/login.jsp");
			return;
		}
		//查询所有学生的信息
		List<Student> stuList = stuDao.list();
		//将数据保存在作用域中
		req.setAttribute("stuList", stuList);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuList.jsp").forward(req, resp);
	}

	/**
	 * 查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String currentPage = req.getParameter("currentPage");
		Integer currentPage_int = 1;
		if ( CommonUtil.isNotEmpty(currentPage) ) {
			currentPage_int = Integer.parseInt(currentPage);
		}
		//查询所有学生的信息
		PageResult<Student> stuResult = stuDao.page(currentPage_int, 10);
		//将数据保存在作用域中
		req.setAttribute("stuResult", stuResult);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuPage.jsp").forward(req, resp);
	}


	/**
	 * 过滤查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
     */
	protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("name = " + name);
		String minAge = req.getParameter("minAge");
		String maxAge = req.getParameter("maxAge");
		Integer minAgeInteger = null;
		Integer maxAgeInteger = null;
		if ( CommonUtil.isNotEmpty(minAge) ) {
			minAgeInteger = new Integer(minAge);
		}
		if ( CommonUtil.isNotEmpty(maxAge) ) {
			maxAgeInteger = new Integer(maxAge);
		}
		List<Student> stuList = stuDao.query(name, minAgeInteger, maxAgeInteger);
		req.setAttribute("stuList", stuList);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuList.jsp").forward(req, resp);
	}

	/**
	 * 分页高级查询
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
     */
	protected void pageQuery(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String minAge = req.getParameter("minAge");
		String maxAge = req.getParameter("maxAge");
		Integer minAgeInteger = null;
		Integer maxAgeInteger = null;
		if ( CommonUtil.isNotEmpty(minAge) ) {
			minAgeInteger = new Integer(minAge);
		}
		if ( CommonUtil.isNotEmpty(maxAge) ) {
			maxAgeInteger = new Integer(maxAge);
		}
		String currentPage = req.getParameter("currentPage");
		Integer currentPageIn = 1;
		if ( CommonUtil.isNotEmpty(currentPage) ) {
			currentPageIn = Integer.parseInt(currentPage);
		}
 		StudentQueryObject stuqo = new StudentQueryObject();
		stuqo.setName(name);
		stuqo.setMaxAge(maxAgeInteger);
		stuqo.setMinAge(minAgeInteger);
		stuqo.setCurrentPage(currentPageIn);
		stuqo.setPageSize(10);
		PageResult<Student> stuResult = stuDao.pageQuery(stuqo);
		req.setAttribute("stuResult", stuResult);
		//进行页面的跳转 请求跳转不需要加上下文路径 由于URL重定向无法访问WEB-INF文件夹下的资源 所有由request进行跳转
		req.getRequestDispatcher("/WEB-INF/smis/views/stuPageQuery.jsp").forward(req, resp);
	}
}
