package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import wang.utils.JdbcUtils;

public class GetT1DATA {
	public Map<String ,String> getAllData(String username){
		
		PreparedStatement statement3=null;
		Connection connection3=null;
		ResultSet rs3=null;
		try{
			connection3= JdbcUtils.getConnection();
			String sql="select * from "+username;
			statement3=connection3.prepareStatement(sql);
			rs3=statement3.executeQuery();
			Map<String,String> map=new HashMap<String, String>();
			while(rs3.next()){
				String key=rs3.getString(1);
				String value=rs3.getString(2);
	//			System.out.println(key+":"+value);
				int index1=key.lastIndexOf("_");
				key=key.substring(0, index1);
				
				if("0".equals(value)){
					value="通过";
				}else if("1".equals(value)){
					value="未通过";
				}else{
					value="配置文件不存在";
				}
			
				map.put(key, value);//通过设置对象来
				
			}
			return map;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection3, statement3, rs3);
		}
		
	}
}


