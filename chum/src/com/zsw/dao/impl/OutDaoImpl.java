package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.OutDao;
import com.zsw.entity.Out;
import com.zsw.util.DBUtil;

public class OutDaoImpl implements OutDao{

	@Override
	public List<Out> QuerySelect() {
		List<Out> outs=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Out out=null;
		ResultSet rs =null;
		try {
			String sql="select o.Out_Date,s.Student_Name,s.Student_Sex,s.Student_Class,o.Out_Remark from outlog o,student s where s.Student_ID=o.Out_StudentID";
			rs=dbUtil.executeQuery(sql, null);
			while(rs.next()) {
				String od=rs.getString("Out_Date");
				String sname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String oremark=rs.getString("Out_Remark");
				out=new Out(od,sname,ssex,sclas,oremark);
				outs.add(out);
			}
			return outs;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}

	@Override
	public List<Out> QuerySelectPage(int currentPage, int pageSize) {
		List<Out> outs=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Out out=null;
		ResultSet rs =null;
		try {
			String sql="select o.Out_Date,s.Student_Name,s.Student_Sex,s.Student_Class,o.Out_Remark from outlog o,student s where s.Student_ID=o.Out_StudentID limit ?,?";
			Object[] params= {(currentPage-1)*pageSize,pageSize};
			rs=dbUtil.executeQuery(sql, params);
			while(rs.next()) {
				String od=rs.getString("Out_Date");
				String sname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String oremark=rs.getString("Out_Remark");
				out=new Out(od,sname,ssex,sclas,oremark);
				outs.add(out);
			}
			return outs;
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			dbUtil.closeAll();
		}
	}
	//添加

	@Override
	public boolean add(Out out) {
		DBUtil dbUtil = new DBUtil();
		String sql="insert into outlog(Out_StudentID,Out_Date,Out_Remark) value(?,?,?)";
		Object[] params = {out.getOut_StudentID(),out.getOut_Date(),out.getOut_Remark()};
		return dbUtil.executeUpdate(sql, params);
	}
	//按学生id删除log
	@Override
	public boolean delbystuid(int id) {
		DBUtil dbUtil = new DBUtil();
		String sql="delete from outlog where Out_StudentID=?";
		Object[] params = {id};
		return dbUtil.executeUpdate(sql, params);
	}
	
}
