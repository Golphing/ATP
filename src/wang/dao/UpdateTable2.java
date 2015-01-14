package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class UpdateTable2 {
	
	public void noFrame(String tableName,String frame_name){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			int pos=tableName.lastIndexOf("_");
			String username=tableName.substring(pos+1);	
			String sql="update "+tableName+" set existed='0' where frameName='"+frame_name.replace(' ', '_').replace('.', '_')+"'";
	//		System.out.println("sql::"+sql);
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
