package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.OutDao;
import com.zsw.entity.Log;
import com.zsw.entity.Out;
import com.zsw.util.DBUtil;

public class OutDaoImpl implements OutDao{

	@Override
	public List<Out> QuerySelect() {
		List<Out> outs=new ArrayList<>();
		Out out=null;
		ResultSet rs =null;
		try {
			String sql="select o.Out_Date,s.Student_Name,s.Student_Sex,s.Student_Class,o.Out_Remark from outlog o,student s where s.Student_ID=o.Out_StudentID";
			rs=DBUtil.executeQuery(sql, null);
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

	@Override
	public List<Out> QuerySelectPage(int currentPage, int pageSize) {
		List<Out> outs=new ArrayList<>();
		Out out=null;
		ResultSet rs =null;
		try {
			String sql="select o.Out_Date,s.Student_Name,s.Student_Sex,s.Student_Class,o.Out_Remark from outlog o,student s where s.Student_ID=o.Out_StudentID limit ?,?";
			Object[] params= {(currentPage-1)*pageSize,pageSize};
			rs=DBUtil.executeQuery(sql, params);
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

}
