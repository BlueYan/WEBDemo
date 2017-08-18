package com.mark.project.springMVCDemo;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Mark_Yan on 2017/8/18.
 */
@Controller
public class FileUploadAndDownload {

	@RequestMapping("/fileUpload")
	public String upload(MultipartFile file) throws Exception {
		//获取文件的一些基本信息
		System.out.println("文件上传名称: " + file.getName());
		System.out.println("文件的真实名称：" + file.getOriginalFilename());
		System.out.println("文件大小: " + file.getSize());
		System.out.println("文件类型：" + file.getContentType());
		//做复制文件的操作
		InputStream is = null;
		OutputStream os = null;
		os = new FileOutputStream("D:/xx.jpg");
		is = file.getInputStream();
		IOUtils.copy(is, os);
		if ( is != null ) {
			is.close();
		}
		if ( os != null ) {
			os.close();
		}
		return "";
	}

	@RequestMapping("/fileDownload")
	public String download(HttpServletResponse response) throws Exception {
		ServletOutputStream sos = response.getOutputStream(); //得打响应输出流
		FileInputStream fis = new FileInputStream("D:/xx.jpg");
		String fileName = "解决下载文件显示中文名字问题";
		fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
		//设置响应头 下载时候提示下载到哪里去
		response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".jpg");
		IOUtils.copy(fis, sos);
		fis.close();
		sos.close();
		return "";
	}

}
