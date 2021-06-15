package com.zsw.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public DBUtil() {
		conn = DataSourceUtil.getConnection();
	}
	
	private PreparedStatement createPreparedStatement(String sql,Object[] params){
		try {
			pstmt = conn.prepareStatement(sql);
			if(params!=null) {
				for(int i=0;i<params.length;i++) {
					pstmt.setObject(i+1, params[i]);
				}
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			System.out.println("PreparedStatement获取失败");
		}
		return pstmt;
	}
	
	
	//通用的增删改
	public boolean executeUpdate(String sql,Object[] params) {
		try {
			pstmt = createPreparedStatement(sql, params);
			int count = pstmt.executeUpdate();
			if (count > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeAll(null, pstmt, conn);
		}
		return false;
	}
	
	//通用的查：通用表示适合与任何查询
	//查询全部学生（很多学生）
	public ResultSet executeQuery(String sql, Object[] params) {
		try {
			pstmt = createPreparedStatement(sql, params);
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void closeAll() {
		DataSourceUtil.closeAll(rs, pstmt, conn);
	}

}
