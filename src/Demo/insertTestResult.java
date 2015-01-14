package Demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.dao.ClearUserTable;
import wang.utils.JdbcUtils;

public class insertTestResult {
	
	public void test(String username){
		PreparedStatement st=null;
		Connection conn=null;
		PreparedStatement statement=null;
		
		ResultSet rs=null;
		try{
			conn=JdbcUtils.getConnection();
			String sql="select * from "+username;
			statement=conn.prepareStatement(sql);
			rs=statement.executeQuery();
			while(rs.next()){
				String name=rs.getString(1);
				String result=rs.getString(2);
		//		System.out.println(name+":"+result);
				int pcrf_pos=name.indexOf("_");
		//		System.out.println(pcrf_pos);
				int pcef_pos=name.indexOf("_", pcrf_pos+1);
		//		System.out.println(pcef_pos);
				int last_pos=name.lastIndexOf("_");
		//		System.out.println(last_pos);
				String yongli=name.substring(pcef_pos+1, last_pos);
				yongli=yongli.replaceAll("_", ".");
				String pcrf=name.substring(0, pcrf_pos);
				String pcef=name.substring(pcrf_pos+1, pcef_pos);
				System.out.println(pcrf);
				System.out.println(pcef);
				System.out.println(yongli);
				System.out.println(result);
				String tableName="";
				if("爱立信".equals(pcrf)){
					tableName="ailixin_pcrf_gx";
				}
				if("华为".equals(pcrf)){
					tableName="huawei_pcrf_gx";
				}
				if("新邮通".equals(pcrf)){
					tableName="xinyoutong_pcrf_gx";
				}
				if("诺基亚".equals(pcrf)){
					tableName="nuojiya_pcrf_gx";
				}
				if("中兴".equals(pcrf)){
					tableName="zhongxing_pcrf_gx";
				}
				if("贝尔".equals(pcrf)){
					tableName="beier_pcrf_gx";
				}
				if("爱立信".equals(pcef)){
					pcef="ailixin_pcef";
				}
				if("华为".equals(pcef)){
					pcef="huawei_pcef";
				}
				
				if("诺基亚".equals(pcef)){
					pcef="nuojiya_pcef";
				}
				if("中兴".equals(pcef)){
					pcef="zhongxing_pcef";
				}
				if("贝尔".equals(pcef)){
					pcef="beier_pcef";
				}
				
				if("0".equals(result)){
					result="通过";
				}else{
					result="未通过";
				}
	//			"update "+tableName+" set existed='0' 
				String sql1="update "+tableName+" set "+pcef+" = '"+result+"' where test_number='"+yongli+"'";
	//			System.out.println(sql1);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(conn, statement, rs);
		}	
	}
}
