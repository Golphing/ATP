package wang.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownLoadLog extends HttpServlet {

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
		boolean lflag=(Boolean) request.getSession().getAttribute("isLogin");
		if(!lflag){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		File logFile=(File) request.getSession().getAttribute("logFile");
		response.setContentType("application/octet-stream");
		response.setContentType("application/OCTET-STREAM;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="+ logFile.getName());

		File file = logFile;
		FileInputStream fis=null;
		BufferedOutputStream out=null;
		try {
		    fis = new FileInputStream(file);
		    out = new BufferedOutputStream(response.getOutputStream());
		    byte[] buffer = new byte[1024];
		    int len;
		    while ((len = fis.read(buffer)) != -1) {
		         out.write(buffer, 0, len);
		         out.flush();
		    }
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException();
		}finally{
			if(out != null)
				out.close();
			if(fis != null)
				fis.close();
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
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
