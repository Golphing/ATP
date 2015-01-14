package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.utils.JdbcUtils;

public class ClearHistoryData {
		public void resetData(){
			PreparedStatement statement=null;
			Connection connection=null;
			ResultSet rs=null;
			try{			
				connection=JdbcUtils.getConnection();
				
				String[] tablenames=new String[]{"huawei_pcrf_gx","nuoxi_pcrf_gx","ailixin_pcrf_gx","zhongxing_pcrf_gx","beier_pcrf_gx","xinyoutong_pcrf_gx"};
				for(String tablename : tablenames){			
					String sql1="drop table if exists "+tablename;
			//		System.out.println(sql1);
					statement=connection.prepareStatement(sql1);
					statement.execute();
					statement.close();
					String sql2="create table "+tablename+"(test_number varchar(30) default '未测试',ailixin_PCEF varchar(50) default '未测试',nuoxi_PCEF varchar(50) default '未测试',zhongxing_PCEF varchar(50) default '未测试',beier_PCEF varchar(50) default '未测试',huawei_PCEF varchar(50) default '未测试')";
					statement=connection.prepareStatement(sql2);
					statement.execute();
					statement.close();
					String sql3="insert into "+tablename+"(test_number) value('5.1.1.1'),('5.1.1.2'),('5.1.1.3'),('5.1.1.4'),('5.1.1.5')";
					statement=connection.prepareStatement(sql3);
					statement.execute();
					statement.close();
				}
			}catch(Exception e){
				e.printStackTrace();
				throw new RuntimeException(e);				
			}finally{
				JdbcUtils.release(connection, statement, rs);
			}	
		}
}
