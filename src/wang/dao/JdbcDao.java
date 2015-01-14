package wang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import wang.domain.User;
import wang.utils.JdbcUtils;

public class JdbcDao {
	
	public  void add(User user){
		PreparedStatement st=null;
		Connection conn=null;
		ResultSet rs=null;
		try{

			conn=JdbcUtils.getConnection();
			String sql="insert into user(name,password) values(?,?)";
			st=conn.prepareStatement(sql);
			st.setString(1, user.getName());
			st.setString(2, user.getPassword());
			st.execute();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}	
		
	}
	
	public  void add1(User user){
		PreparedStatement st=null;
		Connection conn=null;
		ResultSet rs=null;
		try{

			conn=JdbcUtils.getConnection();
			String sql="insert into user(name,password,admin) values(?,?,?)";
			st=conn.prepareStatement(sql);
			st.setString(1, user.getName());
			st.setString(2, user.getPassword());
			st.setString(3, "yes");
			st.execute();
			
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}	
		
	}
	public  boolean find(String name){
		PreparedStatement st=null;
		Connection conn=null;
		ResultSet rs=null;
		try{

			conn=JdbcUtils.getConnection();
			String sql="select count(*) as numb from user where name=?";
			st=conn.prepareStatement(sql);
			st.setString(1, name);
			
			rs=st.executeQuery();
			if(rs != null){
				if(rs.next()){
					if(rs.getInt("numb")>0){
						return true;
					}
					else{
						return false;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
			
		}finally{
			JdbcUtils.release(conn, st, rs);
		}	
		
	}
}
