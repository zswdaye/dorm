package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.TBDao;
import com.zsw.entity.Teacher;
import com.zsw.util.DBUtil;

public class TBDaoImpl implements TBDao{
	//根据老师id查询楼栋id
	@Override
	public List<Integer> QueryBulidID(int id) {
		List<Integer> bs=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		int build;
		ResultSet rs =null;
		try {
			String sql="select * from tb where TB_TeacherID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
			while(rs.next()) {
				build=rs.getInt("TB_BuildingID");				
				bs.add(build);
			}
			return bs;
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
	//根据buildid删除记录
	@Override
	public boolean delbybid(int id) {
		DBUtil dbUtil = new DBUtil();
		String sql="delete from tb where TB_BuildingID=?";
		Object[] params = {id};
		return dbUtil.executeUpdate(sql, params);
	}
	//根据bid查询老师id
	@Override
	public List<Integer> querybybid(int id) {
		List<Integer> ts=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		int tid;
		ResultSet rs =null;
		try {
			String sql="select * from tb where TB_BuildingID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
			while(rs.next()) {
				tid=rs.getInt("TB_TeacherID");				
				ts.add(tid);
			}
			return ts;
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
	//根据tids查询老师
	@Override
	public List<Teacher> QuerySelectById(List<Integer> ts) {
		List<Teacher> teas=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Teacher tea=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from teacher where 1=0");
			ArrayList<Object> param = new ArrayList<Object>();
			for(int i=0;i<ts.size();i++) {
				sql.append(" or Teacher_ID=?");
				param.add(ts.get(i));
			}
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int tid=rs.getInt("Teacher_ID");
				String username=rs.getString("Teacher_Username");
				String password=rs.getString("Teacher_Password");
				String name=rs.getString("Teacher_Name");
				String sex=rs.getString("Teacher_Sex");
				String tel=rs.getString("Teacher_Tel");
				tea=new Teacher(tid,username,password,name,sex,tel);
				teas.add(tea);
			}
			return teas;
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
	public boolean add(int tid, int bid) {
		DBUtil dbUtil = new DBUtil();
		String sql="insert into tb(TB_TeacherID,TB_BuildingID) value(?,?)";
		Object[] params = {tid,bid};
		return dbUtil.executeUpdate(sql, params);
	}
	//删除
	@Override
	public boolean del(int tid, int bid) {
		DBUtil dbUtil = new DBUtil();
		String sql="delete from tb where TB_TeacherID=? and TB_BuildingID=?";
		Object[] params = {tid,bid};
		return dbUtil.executeUpdate(sql, params);
	}
	//按老师id删除tb
	@Override
	public boolean delbyteaid(int id) {
		DBUtil dbUtil = new DBUtil();
		String sql="delete from tb where TB_TeacherID=?";
		Object[] params = {id};
		return dbUtil.executeUpdate(sql, params);
	}
	
}
