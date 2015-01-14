package wang.dao;

import java.io.Writer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mysql.jdbc.ResultSetMetaData;

import wang.utils.JdbcUtils;

public class GetHistory {
	public String getHistory(String tablename){
		PreparedStatement statement=null;
		Connection connection=null;
		ResultSet rs=null;
	
		String return_msg="<table border='1px' cellspacing='0'><tr>";
		try{
			connection= JdbcUtils.getConnection();
			String sql="select * from "+tablename;
			statement=connection.prepareStatement(sql);
			rs=statement.executeQuery();
			ResultSetMetaData rsmd=(ResultSetMetaData) rs.getMetaData();
		//	System.out.println(rsmd.getColumnCount());
			String columnName="";
			int number=rsmd.getColumnCount();
			for(int i=1; i<number+1; i++) {
				columnName=rsmd.getColumnName(i);
				return_msg+="<th>"+columnName+"</th>";
			}
			return_msg+="</tr>";
			while(rs.next()){
				return_msg+="<tr>";
				for(int j=1;j<number+1;j++){
					String colum=rs.getString(j);
					return_msg+="<td>"+colum+"</td>";
				}
				return_msg+="</tr>";
			}
			
			return_msg+="</table>";
	//		System.out.println(return_msg);
			return return_msg;
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(connection, statement, rs);
		}
	}
	
}
