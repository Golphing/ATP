package wang.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;

public class JdbcUtils {
	private static DataSource ds=null;
	
	static{
		InputStream is=JdbcUtils.class.getClassLoader().getResourceAsStream("Properties/dbcpconfig.properties");
		Properties prop=new Properties();
		try {
			prop.load(is);
			BasicDataSourceFactory factory=new BasicDataSourceFactory();
			ds=factory.createDataSource(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
	//	System.out.println(ds);
		return ds.getConnection();
	}
	
public static void release(Connection conn,Statement st,ResultSet rs){
		
		
		if(rs!=null){
			try{
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;

		}
		if(st!=null){
			try{
				st.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		if(conn!=null){
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
}
