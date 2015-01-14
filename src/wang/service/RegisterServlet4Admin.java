package wang.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.dao.JdbcDao;
import wang.domain.User;

public class RegisterServlet4Admin extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public RegisterServlet4Admin() {
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
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password1=request.getParameter("password1");
		String isAdmin=request.getParameter("power");
	//	System.out.println("ladjflaf----------------"+isAdmin);
		boolean flag=false;
		JdbcDao dao=new JdbcDao();
		if(username==null || username.trim().equals("")){
			request.setAttribute("username_msg", "用户名不能为空");
			flag=true;
			
		}
		if(password==null || password.trim().equals("")){
			request.setAttribute("password_msg", "密码不能为空");
			flag=true;
		}
		if(password1==null || password1.trim().equals("")){
			request.setAttribute("password1_msg", "密码不能为空");
			flag=true;
		}
		
		if(!flag){
			if(password1 != null){
				if(!password1.equals(password)){
					flag=true;
					request.setAttribute("password1_msg", "两次密码不一致");
				}
			}
		}
		if(flag){
			request.setAttribute("username", username);
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}else{
			
			if(dao.find(username)){
				request.setAttribute("username", username);
				request.setAttribute("username_msg", "用户名已存在");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}else{
				User user=new User();
				user.setName(username);
				user.setPassword(password);
				dao.add1(user);
				request.setAttribute("message", "注册成功，页面将在&nbsp;<span id='num'>5</span>&nbsp;秒后跳转到登录页面");
				request.getRequestDispatcher("/WEB-INF/jsp/loginsuccess.jsp").forward(request, response);
			}
		}
		dao=null;
		
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
