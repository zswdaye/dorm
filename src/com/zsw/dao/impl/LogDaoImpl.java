package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.zsw.dao.LogDao;
import com.zsw.entity.Log;
import com.zsw.entity.Student;
import com.zsw.util.DBUtil;

public class LogDaoImpl implements LogDao{
	//学生按学生学号查询记录
	@Override
	public List<Log> QuerySelectById(int id) {
		List<Log> logs=new ArrayList<>();
		Log log=null;
		ResultSet rs =null;
	    
		try {
			String sql="select d.Domitory_Name,s.Student_Name,s.Student_Sex,l.Log_Date,l.Log_Remark,t.Teacher_Name from "
					+ "domitory d,student s,log l,teacher t where s.Student_ID=? "
					+ "and d.Domitory_ID=s.Student_DomitoryID and s.Student_ID=l.Log_StudentID and l.Log_TeacherID=t.Teacher_ID";
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
			while(rs.next()) {
				String dname=rs.getString("Domitory_Name");
				String sname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String ldate=rs.getString("Log_Date");
				String lremark=rs.getString("Log_Remark");
				String tname=rs.getString("Teacher_Name");
				log=new Log(dname,sname,ssex,ldate,lremark,tname);
				logs.add(log);
			}
			return logs;
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
	public List<Log> QuerySelectByPage(int id, int currentPage, int pageSize) {
		List<Log> logs=new ArrayList<>();
		Log log=null;
		String sql="select d.Domitory_Name,s.Student_Name,s.Student_Sex,l.Log_Date,l.Log_Remark,t.Teacher_Name from " 
				+ "domitory d,student s,log l,teacher t where s.Student_ID=? " 
				+ "and d.Domitory_ID=s.Student_DomitoryID and s.Student_ID=l.Log_StudentID and l.Log_TeacherID=t.Teacher_ID limit ?,?";
		Object[] params= {id,(currentPage-1)*pageSize,pageSize};
		ResultSet rs=DBUtil.executeQuery(sql, params);
		try {
			while(rs.next()) {
				String dname=rs.getString("Domitory_Name");
				String sname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String ldate=rs.getString("Log_Date");
				String lremark=rs.getString("Log_Remark");
				String tname=rs.getString("Teacher_Name");
				log=new Log(dname,sname,ssex,ldate,lremark,tname);
				logs.add(log);
			}
			return logs;
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
	//按楼栋id查询
	@Override
	public List<Log> QuerySelectbdById(int id,Student stu) {
		List<Log> logs=new ArrayList<>();
		Log log=null;
		ResultSet rs =null;
	    
		try {
			StringBuilder sql =new StringBuilder("select l.Log_ID, d.Domitory_Name,s.Student_Name,s.Student_Sex,s.Student_Class,l.Log_Date,l.Log_Remark,t.Teacher_Name from"
					+ " domitory d,student s,log l,teacher t where d.Domitory_ID=s.Student_DomitoryID and s.Student_ID=l.Log_StudentID and t.Teacher_ID=l.Log_TeacherID and"
					+ " d.Domitory_BuildingID=?");
			ArrayList<Object> param = new ArrayList<Object>();
			param.add(id);
			String dname=stu.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and s.Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and s.Student_Username=?");
				param.add(susername);
			}
			String sclass=stu.getStudent_Class();
			if(sclass!=null && !sclass.trim().isEmpty()) {
				sql.append(" and s.Student_Class=?");
				param.add(sclass);
			}
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int lid=rs.getInt("Log_ID");
				String doname=rs.getString("Domitory_Name");
				String stname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String ldate=rs.getString("Log_Date");
				String lremark=rs.getString("Log_Remark");
				String tname=rs.getString("Teacher_Name");
				log=new Log(lid,doname,stname,ssex,sclas,ldate,lremark,tname);
				logs.add(log);
			}
			return logs;
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
	public List<Log> QuerySelectbdByPage(int id,Student stu, int currentPage, int pageSize) {
		List<Log> logs=new ArrayList<>();
		Log log=null;
		ResultSet rs =null;
	    
		try {
			StringBuilder sql =new StringBuilder("select l.Log_ID, d.Domitory_Name,s.Student_Name,s.Student_Sex,s.Student_Class,l.Log_Date,l.Log_Remark,t.Teacher_Name from"
					+ " domitory d,student s,log l,teacher t where d.Domitory_ID=s.Student_DomitoryID and s.Student_ID=l.Log_StudentID and t.Teacher_ID=l.Log_TeacherID and"
					+ " d.Domitory_BuildingID=?");
			ArrayList<Object> param = new ArrayList<Object>();
			param.add(id);
			String dname=stu.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String sname=stu.getStudent_Name();
			if(sname!=null && !sname.trim().isEmpty()) {
				sql.append(" and s.Student_Name=?");
				param.add(sname);
			}
			String susername=stu.getStudent_Username();
			if(susername!=null && !susername.trim().isEmpty()) {
				sql.append(" and s.Student_Username=?");
				param.add(susername);
			}
			String sclass=stu.getStudent_Class();
			if(sclass!=null && !sclass.trim().isEmpty()) {
				sql.append(" and s.Student_Class=?");
				param.add(sclass);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int lid=rs.getInt("Log_ID");
				String doname=rs.getString("Domitory_Name");
				String stname=rs.getString("Student_Name");
				String ssex=rs.getString("Student_Sex");
				String sclas=rs.getString("Student_Class");
				String ldate=rs.getString("Log_Date");
				String lremark=rs.getString("Log_Remark");
				String tname=rs.getString("Teacher_Name");
				log=new Log(lid,doname,stname,ssex,sclas,ldate,lremark,tname);
				logs.add(log);
			}
			return logs;
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
	//插入缺寝记录
	@Override
	public boolean addLog(Log log) {
		String sql="insert into log(Log_StudentID,Log_TeacherID,Log_Date,Log_Remark) value(?,?,?,?)";
		Object[] params = {log.getLog_StudentID(),log.getLog_TeacherID(),log.getLog_Date(),log.getLog_Remark()};
		return DBUtil.executeUpdate(sql, params);
	}

}
