package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.Test;

import wang.utils.JdbcUtils;

public class FinalUpdateT1 {
	private String table1Name;

	public FinalUpdateT1(String table1Name) {
		super();
		this.table1Name = table1Name;
	}
	@Test
	public void updateT1(){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
		try{
			
			String table2Name="frame2pcap_"+table1Name;
			String sql="select packet_name from "+table1Name +" where check_result='0'";
	//		System.out.println(sql);
			connection =JdbcUtils.getConnection();
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			
			while(rs.next()){			//第一重循环，找到所有的没有错误的包
				String field1=rs.getString(1);
	//			System.out.println(field1);
				PreparedStatement statement1=null;
				Connection connection1=null;
				ResultSet rs1=null;
				try{
					
					String sql1="select frameName from "+table2Name+" where existed='1' and packet_name='"+field1+"'";
			//		System.out.println(sql1);
					connection1= JdbcUtils.getConnection();
					statement1=connection1.prepareStatement(sql1);
					rs1=statement1.executeQuery();
					while(rs1.next()){					//从第二个包中逐个找出属于第一个包的子Frame
						String field2=rs1.getString(1);
		//				System.out.println(field2);
						PreparedStatement statement2=null;
						Connection connection2=null;
						ResultSet rs2=null;
						try{
							connection2= JdbcUtils.getConnection();
							String sql3="select count(*) as num from "+field2+" where final_result='未通过'";
							statement2=connection2.prepareStatement(sql3);
							rs2=statement2.executeQuery();
							if(rs2.next()){
								int num=rs2.getInt(1);
				//				System.out.println(num);
								if(num>0){
									PreparedStatement statement3=null;
									Connection connection3=null;
									ResultSet rs3=null;
									try{
										connection3= JdbcUtils.getConnection();
										String sql4="update "+table1Name+" set check_result='1' where packet_name='"+field1+"'";
										statement3=connection3.prepareStatement(sql4);
										statement3.execute();
										break;
									}catch(Exception e){
										e.printStackTrace();
					//				throw new RuntimeException(e);
										
									}finally{
										JdbcUtils.release(connection3, statement3, rs3);
									}
								}
							}
						
							
						}catch(Exception e){
							e.printStackTrace();
				//			throw new RuntimeException(e);
							
						}finally{
							JdbcUtils.release(connection2, statement2, rs2);
						}
					}
					
				}catch(Exception e){
					e.printStackTrace();
			//		throw new RuntimeException(e);
					
				}finally{
					JdbcUtils.release(connection1, statement1, rs1);
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
	//		throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
	}
}
