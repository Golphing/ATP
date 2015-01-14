package wang.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.dao.C_Table1_T2;
import wang.utils.JdbcUtils;



public class LoginServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		Writer writer=response.getWriter();
		PreparedStatement st=null;
		Connection conn=null;
		ResultSet rs=null;
		request.setCharacterEncoding("UTF-8");
		try{
			//response.setContentType("text/html;charset=UTF-8");
		//	request.setCharacterEncoding("UTF-8");
			String id=request.getParameter("username");
			String paswd=request.getParameter("password");
			conn=JdbcUtils.getConnection();
			String sql="select count(*) as numb from user where name=? and password=?";
			st=conn.prepareStatement(sql);
			st.setString(1, id);
			st.setString(2, paswd);
			rs=st.executeQuery();
			if(rs.next()){
				if(rs.getInt("numb")>0){
					request.getSession().setAttribute("user",id);
					request.getSession().setAttribute("isLogin", true);
					C_Table1_T2 ct12=new C_Table1_T2();
					ct12.crePackList(id);
					ct12=null;
					request.getRequestDispatcher("/WEB-INF/jsp/select.jsp").forward(request, response);
				}
				else{
					request.setAttribute("message", "用户名或密码输入错误，请重新输入!");
					request.setAttribute("username", id);
					request.getRequestDispatcher("/login.jsp").forward(request, response);
					return;
				}
			}
		}catch(Exception e){
			request.setAttribute("message", "数据库连接错误!");
			request.getRequestDispatcher("/WEB-INF/jsp/dberror.jsp").forward(request, response);
			
		}finally{
			JdbcUtils.release(conn, st, rs);
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

		doGet(request,response);
	}

}
