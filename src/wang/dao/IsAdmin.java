package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class IsAdmin {
	public String isadministrator(String username){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			connection= JdbcUtils.getConnection();
			String sql="select admin from user where name='"+username+"'";
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			String isAdmin="";
			if(rs.next()){
				isAdmin=rs.getString(1);
			}
			return isAdmin;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
	}
}
