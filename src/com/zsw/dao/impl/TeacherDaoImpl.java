package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.TeacherDao;
import com.zsw.entity.Teacher;
import com.zsw.util.DBUtil;

public class TeacherDaoImpl implements TeacherDao{

	@Override
	public int CheckLogin(String username, String password) {
		int id =0;
		ResultSet rs =null;
		try {
			String sql="select * from teacher where Teacher_Username=? and Teacher_Password=?";
			Object[] params= {username,password};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				id = rs.getInt("Teacher_ID");
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
	//查询
	@Override
	public Teacher QuerySelectById(int id) {
		Teacher teacher=null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from teacher where Teacher_ID=?";
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Teacher_ID");
				String username=rs.getString("Teacher_Username");
				String password=rs.getString("Teacher_Password");
				String name=rs.getString("Teacher_Name");
				String sex=rs.getString("Teacher_Sex");
				String tel=rs.getString("Teacher_Tel");
				teacher=new Teacher(no,username,password,name,sex,tel);
			}
			return teacher;
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
	//查询全部
	@Override
	public List<Teacher> QuerySelectAll(Teacher tea) {
		List<Teacher> teas=new ArrayList<>();
		Teacher teacher=null;
		ResultSet rs =null;
	    
		try {
			StringBuilder sql =new StringBuilder("select * from teacher where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			String tname=tea.getTeacher_Name();
			if(tname!=null && !tname.trim().isEmpty()) {
				sql.append(" and Teacher_Name=?");
				param.add(tname);
			}
			String tusername=tea.getTeacher_Username();
			if(tusername!=null && !tusername.trim().isEmpty()) {
				sql.append(" and Teacher_Username=?");
				param.add(tusername);
			}
			String ttel=tea.getTeacher_Tel();
			if(ttel!=null && !ttel.trim().isEmpty()) {
				sql.append(" and Teacher_Tel=?");
				param.add(ttel);
			}
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int id=rs.getInt("Teacher_ID");
				String username=rs.getString("Teacher_Username");
				String password=rs.getString("Teacher_Password");
				String name=rs.getString("Teacher_Name");
				String sex=rs.getString("Teacher_Sex");
				String tel=rs.getString("Teacher_Tel");
				teacher=new Teacher(id,username,password,name,sex,tel);
				teas.add(teacher);
			}
			return teas;
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
	public List<Teacher> QuerySelectAllPage(Teacher tea,int currentPage, int pageSize) {
		List<Teacher> teas=new ArrayList<>();
		Teacher teacher=null;
		ResultSet rs =null;
	    
		try {
			StringBuilder sql =new StringBuilder("select * from teacher where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			String tname=tea.getTeacher_Name();
			if(tname!=null && !tname.trim().isEmpty()) {
				sql.append(" and Teacher_Name=?");
				param.add(tname);
			}
			String tusername=tea.getTeacher_Username();
			if(tusername!=null && !tusername.trim().isEmpty()) {
				sql.append(" and Teacher_Username=?");
				param.add(tusername);
			}
			String ttel=tea.getTeacher_Tel();
			if(ttel!=null && !ttel.trim().isEmpty()) {
				sql.append(" and Teacher_Tel=?");
				param.add(ttel);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int id=rs.getInt("Teacher_ID");
				String username=rs.getString("Teacher_Username");
				String password=rs.getString("Teacher_Password");
				String name=rs.getString("Teacher_Name");
				String sex=rs.getString("Teacher_Sex");
				String tel=rs.getString("Teacher_Tel");
				teacher=new Teacher(id,username,password,name,sex,tel);
				teas.add(teacher);
			}
			return teas;
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
	public boolean update(int id, Teacher teacher) {
		String sql="update teacher set Teacher_Username=?,Teacher_Password=?,Teacher_Name=?,Teacher_Sex=?,Teacher_Tel=? where Teacher_ID=?";
		Object[] params= {teacher.getTeacher_Username(),teacher.getTeacher_Password(),teacher.getTeacher_Name(),teacher.getTeacher_Sex(),teacher.getTeacher_Tel(),id};
		return DBUtil.executeUpdate(sql, params);
	}
	//检查是否有相同的用户名
	@Override
	public int Checkuser(String username) {
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from teacher where Teacher_Username=?";
			Object[] params= {username};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				num=rs.getInt(1);
			}
			return num;
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
	//添加
	@Override
	public boolean add(Teacher tea) {
		String sql="insert into teacher(Teacher_Username,Teacher_Password,Teacher_Name,Teacher_Sex,Teacher_Tel) value(?,?,?,?,?)";
		Object[] params = {tea.getTeacher_Username(),tea.getTeacher_Password(),tea.getTeacher_Name(),tea.getTeacher_Sex(),tea.getTeacher_Tel()};
		return DBUtil.executeUpdate(sql, params);
	}
	//删除
	@Override
	public boolean del(int id) {
		String sql="delete from teacher where Teacher_ID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	
	

}
