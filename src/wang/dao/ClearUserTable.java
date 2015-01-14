package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import wang.utils.JdbcUtils;

public class ClearUserTable {
	public void getAllTable(String username){

		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			connection=JdbcUtils.getConnection();
			String sql="select frameName from frame2pcap_"+username;
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
		
			while(rs.next()){
				String table_name=rs.getString(1);
			
				PreparedStatement statement1=null;
				Connection connection1=null;
				ResultSet rs1=null;
				try{
					connection1=JdbcUtils.getConnection();
					String sql1="drop table if exists "+table_name;
					statement1=connection1.prepareStatement(sql1);
					statement1.execute();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					JdbcUtils.release(connection1, statement1, rs1);
				}
			}
			
		}catch(Exception e){
			System.out.println("已删除");
	//		e.printStackTrace();
		//	throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
		
	}
}
