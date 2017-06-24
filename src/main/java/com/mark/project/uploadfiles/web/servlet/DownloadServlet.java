package com.mark.project.uploadfiles.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Administrator on 2017/6/24.
 */
@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

	/**
	 * 下载文件操作
	 *
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String fileName = req.getParameter("fileName");
		byte[] bytes = fileName.getBytes("ISO-8859-1"); //先解码
		fileName = new String(bytes, "UTF-8"); //再编码 解决中文乱码问题
		System.out.println("fileName = " + fileName);
		String path = super.getServletContext().getRealPath("/WEB-INF/download"); //获取当前下载文件的真实路径
		File file = new File(path, fileName);
		resp.setHeader("Content-Disposition", "attachment; filename=" + fileName); //设置响应头 解决下载文件时候提示框没有显示下载文件的名字
		String head = req.getHeader("User-Agent");
		if (head.contains("MSIE")) {
			//IE浏览器
			resp.setHeader("Content-Disposition", "attachment;filename="
					+ URLEncoder.encode(fileName, "UTF-8")); //解决IE浏览器下 下载中文名字文件的问题
		} else {
			//非IE浏览器
			resp.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
		}

		Files.copy(Paths.get(file.getAbsolutePath()), resp.getOutputStream());
	}
}