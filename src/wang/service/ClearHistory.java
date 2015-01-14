package wang.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.dao.ClearHistoryData;
import wang.dao.IsAdmin;

public class ClearHistory extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ClearHistory() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		Writer writer=response.getWriter();
		boolean lflag=(Boolean) request.getSession().getAttribute("isLogin");
		if(!lflag){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		String username=(String) request.getSession().getAttribute("user");
		IsAdmin isadmin=new IsAdmin();
		String yorn=isadmin.isadministrator(username);
		if("yes".equals(yorn)){			
			ClearHistoryData chd=new ClearHistoryData();
			chd.resetData();
			writer.write("清除成功！");
		}else{
			writer.write("您不具有此项操作权限！");
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
