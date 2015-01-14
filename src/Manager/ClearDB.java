package Manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wang.utils.JdbcUtils;

public class ClearDB extends HttpServlet {

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

		response.setContentType("text/html");
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			
			connection=JdbcUtils.getConnection();
			

			String sql1="drop database IF EXISTS ATP_DATA";
			String sql2="create database ATP_DATA";
			String sql3="use ATP_DATA";
			String sql4="create table user(name varchar(60),password varchar(60))";
			statement=connection.prepareStatement(sql1);
			statement.addBatch(sql1);
			statement.addBatch(sql2);
			statement.addBatch(sql3);
			statement.addBatch(sql4);
			statement.executeBatch();
			response.getWriter().write("successful!");
	
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
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
