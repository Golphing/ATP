package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import wang.utils.JdbcUtils;

public class Write2Table1 {
		
	public void insertPcapFile(String tableName,String fieldValue) throws SQLException{
		
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			
			connection= JdbcUtils.getConnection();
			String sql="insert into "+tableName+"(packet_name) values('"+fieldValue.replace('.', '_')+"')";
	//		System.out.println(sql);
			statement=connection.prepareStatement(sql);
			statement.execute();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}	
	}
	
	
	
	public void updateResult(){
		
	}
}
