package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class Write2Table2 {
	
	public void insert2T2(String tableName,String volum1,String volum2){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			volum1=volum1.replace(' ', '_').replace('.', '_');
			volum2=volum2.replace('.', '_');
			connection= JdbcUtils.getConnection();
			String sql="insert into "+tableName+"(frameName,packet_name) values('"+volum1+"','"+volum2+"')";			
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
