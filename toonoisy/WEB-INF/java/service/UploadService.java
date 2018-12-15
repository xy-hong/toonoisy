package service;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import util.UUIDUtils;

public class UploadService {
	
	Map<String,List<FileItem>> map ;
	HttpServletRequest request;
	
	public UploadService(HttpServletRequest request) {
		this.request = request;
		
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024*10, new File("tmp"));
		ServletFileUpload up = new ServletFileUpload(factory);
		up.setFileSizeMax(1024*1024*10);
		
		try {
			this.map = up.parseParameterMap(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getText(String textName) {
		return map.get(textName).get(0).getString();
	}
	
	public String upload(String fileName, String path) {
		FileItem item = map.get(fileName).get(0);
		

		String name =UUIDUtils.getUUID()+item.getName();
		//String name =item.getName();
		String url = request.getServletContext().getRealPath(path)+File.separator+name;
		
		try {
			item.write(new File(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	/**
	 * 
	 * @param request http请求
	 * @param fileParam 文件对应的表单参数name
	 * @param path 保存到文件夹
	 * @return
	 
	public String upload(HttpServletRequest request, String fileParam, String path ) {
		
		DiskFileItemFactory factory = new DiskFileItemFactory(1024*1024*10, new File("tmp"));
		ServletFileUpload up = new ServletFileUpload(factory);
		up.setFileSizeMax(1024*1024*10);
		
		FileItem item = null;
		try {
			Map<String,List<FileItem>> map = up.parseParameterMap(request);
			List<FileItem> list = map.get(fileParam);
			 item = list.get(0);
			 System.out.println(map.get("username").get(0).getString());
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fileName = item.getName();
		String url = request.getServletContext().getRealPath(path)+File.separator+fileName;
		
		try {
			item.write(new File(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return fileName;
	}*/
}
