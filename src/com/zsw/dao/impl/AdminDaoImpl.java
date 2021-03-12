package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.zsw.dao.AdminDao;
import com.zsw.entity.Admin;
import com.zsw.util.DBUtil;

public class AdminDaoImpl implements AdminDao{

	@Override
	public int CheckLogin(String username, String password) {
		int id =0;
		ResultSet rs =null;
		try {
			String sql="select * from admin where Admin_Username=? and Admin_Password=?";
			Object[] params= {username,password};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				id = rs.getInt("Admin_ID");
			}
			return id;
		}catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}
	//按ID查询
	@Override
	public Admin QuerySelectById(int id) {
		Admin admin=null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from admin where Admin_ID=?";
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Admin_ID");
				String username=rs.getString("Admin_Username");
				String password=rs.getString("Admin_Password");
				String name=rs.getString("Admin_Name");
				String sex=rs.getString("Admin_Sex");
				String tel=rs.getString("Admin_Tel");
				admin=new Admin(no,username,password,name,sex,tel);
			}
			return admin;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		
	}

	//修改
	@Override
	public boolean Update(int id,Admin admin) {
		String sql="update admin set Admin_Password=?,Admin_Name=?,Admin_Sex=?,Admin_Tel=? where Admin_ID=?";
		Object[] params = {admin.getAdmin_Password(),admin.getAdmin_Name(),admin.getAdmin_Sex(),admin.getAdmin_Tel(),id};
		return DBUtil.executeUpdate(sql, params);
	}

}
