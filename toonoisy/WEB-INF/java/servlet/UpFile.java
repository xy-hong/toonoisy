package servlet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UploadService;
import toonoisy.Message;
import toonoisy.RoomManger;
import util.MyTimeUtil;

/**
 * Servlet implementation class UpFile
 */
@WebServlet("/UpFile")
public class UpFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
UploadService us = new UploadService(request);
		
		String username = us.getText("username");
		String receive = us.getText("room");
		System.out.println(username+"/////"+receive);
		
		String fileName = us.upload("file", "file");
	   
		
		Message message = new Message();
		message.setType("file");
		message.setSend(username);
		message.setReceive(receive);
		message.setCreateTime(MyTimeUtil.getCurrentTime());
		message.setData(fileName);
		
		RoomManger.getInstance().get(receive).sendEveryone(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
