package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import wang.utils.JdbcUtils;

public class Write2Table3 {
	private String table_name;
	public void createTable3(String tableName){
		
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			
			connection=JdbcUtils.getConnection();
			table_name=tableName.replace(' ', '_').replace('.', '_');
		//	String sql=" create table IF NOT EXISTS "+tableName+" (packet_name varchar(100),check_result varchar(30) default '0')";
			String sql="create table if not exists "+tableName.replace(' ', '_').replace('.', '_')+"(AVPName varchar(100),mustOrNot varchar(30) default '必选',preset_value varchar(300),actual_value varchar(300),final_result varchar(30))";
		//	System.out.println(sql);
			statement=connection.prepareStatement(sql);
			statement.execute();
			String sql1="delete from "+tableName.replace(' ', '_').replace('.', '_');
			statement=connection.prepareStatement(sql1);
			statement.execute();
	
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}	
	}
	
	
	public void insert2T3(String AVPName,String mustOrNot,String preset_value,String actual_value,String final_result){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			
			connection=JdbcUtils.getConnection();
			String sql="insert into "+table_name+" values(?,?,?,?,?) ";
			statement=connection.prepareStatement(sql);
			statement.setString(1, AVPName);
			statement.setString(2, mustOrNot);
			statement.setString(3, preset_value);
			statement.setString(4, actual_value);
			statement.setString(5, final_result);
			statement.execute();
		
	
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
	}
	@Test
	public void test(){
		String str="nihao.5.1.1.1";
		String new1=str.replace('.', '_');
		System.out.println(new1);
		String s2="fame 1";
		String new2=s2.replace(' ', '_');
		System.out.println(new2);
	}
}


