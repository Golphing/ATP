package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import wang.utils.JdbcUtils;

public class C_Table1_T2 {
	
	
	public void crePackList(String username){
		//	对每一个用户创建一张数据表保存用户操作的总包列表
		PreparedStatement st=null;
		Connection conn=null;
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			ClearUserTable cut=new ClearUserTable();
			cut.getAllTable(username);
			String table1Name=username;
			String table2Name="Frame2Pcap_"+table1Name;
			connection=JdbcUtils.getConnection();
			String sql=" create table IF NOT EXISTS "+table1Name+" (packet_name varchar(100),check_result varchar(30) default '0')";	
			statement=connection.prepareStatement(sql);
	
			String sql1="delete from "+table1Name;
			statement=connection.prepareStatement(sql1);
		//	System.out.println(sql1);
			String sql3=" create table IF NOT EXISTS "+table2Name+" (frameName varchar(100),packet_name varchar(100),existed varchar(10) default '1') ";
		//	System.out.println(sql3);
			statement=connection.prepareStatement(sql3);

			String sql4="delete from "+table2Name;
			statement=connection.prepareStatement(sql4);
		//	System.out.println(sql4);
			statement.addBatch(sql);
			statement.addBatch(sql1);
	
			statement.addBatch(sql3);
			statement.addBatch(sql4);
			statement.executeBatch();
			statement.clearBatch();
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}	
			
	}
	
/*	public void crePack(String name){
		//	用户每导入一个包就会为这个包创建一张数据包，记录此包中的检查点检查情况，
		// 	同时在创建该表的时候要在上面创建的那张表中记录下这张表的名字
	}
	
	public void insert2crePack(){
		// 	将检测到的数据插入到crePack表中。
	}*/
	
/*	@Test
	public void test(){
		PreparedStatement st=null;
		Connection conn=null;
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			connection=JdbcUtils.getConnection();
			String sql="select * from aa";
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next()){
				String val=rs.getString(1);
				System.out.println(val);
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}	
	}*/
	
	
	

	
}
