package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class Insert2History {
	public void insert2histo(String sql){
		Connection conn=null;
		PreparedStatement statement=null;		
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			statement=conn.prepareStatement(sql);
			statement.execute();
			System.out.println("inset2his");
		}catch(Exception e){
			e.printStackTrace();
	//		throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(conn, statement, rs);
		}	
	}
}
