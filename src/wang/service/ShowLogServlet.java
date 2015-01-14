package wang.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.utils.FileDao;

public class ShowLogServlet extends HttpServlet {

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
		response.setContentType("text/html;charset=utf-8");
		String log_path=(String) request.getSession().getAttribute("logpath");
		File log_file=new File(log_path);
		if(log_file.exists()){
			FileInputStream fis=new FileInputStream(log_file);
			InputStreamReader isr=new InputStreamReader(fis, "UTF-8");
			BufferedReader bufr=new BufferedReader(isr);
			String log_content="";
			String buff="";
			while((buff=bufr.readLine())!=null){
				log_content+=buff;
				log_content+="<br>";
				
			}
			bufr.close();
			isr.close();
			fis.close();
		/*	char[] buf=new char[1024];
			int length=0;
			String log_content="";
			while((length=isr.read(buf))>0){
				log_content+=new String(buf, 0, length);
				
			}*/
			/*String regEx="\\r"; 
			Pattern pat=Pattern.compile(regEx);  
			Matcher mat=pat.matcher(log_content);  
			log_content=mat.replaceAll("<br>"); 
			System.out.println(log_content);*/
			request.setAttribute("log_content", log_content);
			request.getRequestDispatcher("/WEB-INF/jsp/showLog.jsp").forward(request, response);
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

			doGet(request, response);
	}

}
