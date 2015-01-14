package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import wang.utils.JdbcUtils;

public class GetT3DATA {
	
	public String getAllData(String tableName){
		PreparedStatement statement3=null;
		Connection connection3=null;
		ResultSet rs3=null;
		
		try{
			connection3=JdbcUtils.getConnection();
			String sql="select * from "+tableName;
	//		System.out.println(sql);
			statement3=connection3.prepareStatement(sql);
			rs3=statement3.executeQuery();
			String out_put="<table cellspacing=0 border='1px'>";
			out_put+="<tr><th>AVPName</th><th>MustOrNot</th><th>preset_value</th><th>actual_value</th><th>final_result</th></tr>";
			while(rs3.next()){
				out_put+="<tr>";
				String avp_name=rs3.getString(1);
				String mustOrNot=rs3.getString(2);
				String preset_value=rs3.getString(3);
				String actual_value=rs3.getString(4);
				String final_result=rs3.getString(5);
				if("未通过".equals(final_result)){
					final_result="<span id='redColor'>"+final_result+"</span>";
				}
				else if("通过".equals(final_result)){
					final_result="<span id='greenColor'>"+final_result+"</span>";
				}else{
					final_result="<span id='blueColor'>"+final_result+"</span>";
				}
				out_put+="<td>"+avp_name+"</td>";
				out_put+="<td>"+mustOrNot+"</td>";
				out_put+="<td>"+preset_value+"</td>";
				out_put+="<td>"+actual_value+"</td>";
				out_put+="<td>"+final_result+"</td>";
				out_put+="</tr>";
			}
			out_put+="</table>";
	//		System.out.println(out_put);
			return out_put;
		}
		catch(SQLException e1){
			String out="在导入的包中未找到对应的Frame模块";
	//		System.out.println("在导入的包中未找到对应的Frame模块");
			return out;
		}
		catch(Exception e){
			e.printStackTrace();
	//		throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection3, statement3, rs3);
		}
		return null;
	}
}
