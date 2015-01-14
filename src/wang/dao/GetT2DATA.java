package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wang.utils.JdbcUtils;

public class GetT2DATA {
	
	public List<String> getAllData(String field2,String username){
		PreparedStatement statement3=null;
		Connection connection3=null;
		ResultSet rs3=null;
		String table2_name="frame2pcap"+"_"+username;
		try{
			connection3=JdbcUtils.getConnection();
			String sql="select frameName,existed from "+table2_name+" where packet_name='"+field2+"'";
			statement3=connection3.prepareStatement(sql);
			rs3=statement3.executeQuery();
			List<String> list=new ArrayList<String>();
			while(rs3.next()){
				String frameName=rs3.getString(1);
				int index1=frameName.indexOf("_");
				int index2=frameName.indexOf("_", index1+1);
				String aa=frameName.substring(0, index2);
	//			System.out.println("************"+aa);
				String existed=rs3.getString(2);
		//		System.out.println(frameName+":"+existed);
				
				if("0".equals(existed)){
					frameName=frameName+":未找到对应的Frame";
				}
				list.add(frameName);
			}
			return list;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection3, statement3, rs3);
		}
		
		
	}
}
