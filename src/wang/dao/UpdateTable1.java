package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class UpdateTable1 {
	
	public void fileError(String sql){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			connection= JdbcUtils.getConnection();
			statement=connection.prepareStatement(sql);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
	}
}
