package com.mark.project.uploadfiles.util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2017/6/22.
 * 文件上传工具类
 * upload.setFileSizeMax(1024 * 100); //可以设置文件的大小
 * upload.setSizeMax(1024 * 100); //设置整个请求的大小
 * 还可以针对文件类型进行过滤等
 */
public class FileUploadUtil {


	public static void upload(HttpServletRequest req) {
		String uploadPath = req.getServletContext().getRealPath("/upload");
		System.out.println("uploadPath = " + uploadPath);
		//判断是否是以文件形式上传 multipart开始的格式
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if ( !isMultipart ) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory(); //创建工厂模式
		ServletFileUpload upload = new ServletFileUpload(factory); //创建文件上传处理对象
		try {
			/**
			 * 解析请求
			 * 每一个请求都是一个FileItem对象
			 */
			List<FileItem> items = upload.parseRequest(req); //解析请求
			for ( FileItem item : items ) {
				if ( item.isFormField() ) {
					//表示是普通的控件 <input type="不是file">
					System.out.println("fieldName = " + item.getFieldName()); //获取表单元素中name的属性值
					System.out.println("value = " + item.getString("UTF-8")); //获取对应的值
				} else {
					System.out.println("文件上传的名字 = " + item.getName());
					item.write(new File(uploadPath, item.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
