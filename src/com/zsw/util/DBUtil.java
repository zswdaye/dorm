package com.zsw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static final String URL="jdbc:mysql://localhost:3306/sushe";
	private static final String username="root";
	private static final String password="236183";
	public static Connection connection =null;
	public static PreparedStatement pstmt=null;
	public static ResultSet rs =null;
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(URL,username,password);
	}
	public static PreparedStatement createPreparedStatement(String sql,Object[] params) throws ClassNotFoundException, SQLException {
		pstmt=getConnection().prepareStatement(sql);
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(connection!=null) connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//通用的增删改
	public static boolean executeUpdate(String sql,Object[] params) {
		
		try {
			
//			String sql = "delete from student where sno=?";
//			pstmt=getConnection().prepareStatement(sql);			
//			pstmt.setInt(1, sno);		
			//setXxx()方法的个数依赖于?的个数，而?的个数又和数组params的个数一致
			//setXxx()方法的个数->数组params的个数一致
			pstmt=createPreparedStatement(sql, params);
			
			int count=pstmt.executeUpdate();
			if(count>0) {
				return true;
			}else {
				return false;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}finally {
			closeAll(null, pstmt, connection);
		}
	}
	
	//通用的查：通用表示适合与任何查询
	//查询全部学生（很多学生）
	public static ResultSet executeQuery(String sql,Object[] params) {
	
		try {
			
//			String sql = "select * from student";
			pstmt=createPreparedStatement(sql, params);
			
			rs=pstmt.executeQuery();			
			return rs;
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
//		finally {
//			try {
//				if(rs!=null) rs.close();
//				if(pstmt!=null) pstmt.close();
//				if(connection!=null) connection.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		
	}
	
	//查询总数
	public static int getTotalCount(String sql) {
		int count =-1;
		try {
			pstmt = createPreparedStatement(sql, null);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getInt(1);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			closeAll(rs, pstmt, connection);
		}
		return count;
	}
}
