package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.BuildDao;
import com.zsw.entity.Building;
import com.zsw.util.DBUtil;

public class BuildDaoImpl implements BuildDao{
	//根据id查询building
	@Override
	public List<Building> QuerySelectById(List<Integer> bs) {
		List<Building> builds=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Building build=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from building where 1=0");
			ArrayList<Object> param = new ArrayList<Object>();
			for(int i=0;i<bs.size();i++) {
				sql.append(" or Building_ID=?");
				param.add(bs.get(i));
			}
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int bid=rs.getInt("Building_ID");
				String name=rs.getString("Building_Name");
				build=new Building(bid,name);
				builds.add(build);
			}
			return builds;
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
	//查询所有building
	@Override
	public List<Building> QuerySelectAll() {
		List<Building> builds=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Building build=null;
		ResultSet rs =null;
		try {
			String sql ="select * from building";		
			rs=dbUtil.executeQuery(sql, null);
			while(rs.next()) {
				int bid=rs.getInt("Building_ID");
				String name=rs.getString("Building_Name");
				build=new Building(bid,name);
				builds.add(build);
			}
			return builds;
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
	//根据id查询单个building
	@Override
	public Building queryselect(int id) {
		DBUtil dbUtil = new DBUtil();
		Building build =null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from building where Building_ID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Building_ID");
				String name=rs.getString("Building_Name");
				String type=rs.getString("Building_Introduction");
				build=new Building(no,name,type);
			}
			return build;
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
	//根据名称查询所有building
	@Override
	public List<Building> QuerySelectByName(String name) {
		List<Building> builds=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Building build=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from building where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			
			if(name!=null && !name.trim().isEmpty()) {
				sql.append(" and Building_Name=?");
				param.add(name);
			}

			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int bid=rs.getInt("Building_ID");
				String bname=rs.getString("Building_Name");
				String intro=rs.getString("Building_Introduction");
				build=new Building(bid,bname,intro);
				builds.add(build);
			}
			return builds;
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
	public List<Building> QuerySelectByNamePage(String name, int currentPage, int pageSize) {
		List<Building> builds=new ArrayList<>();
		DBUtil dbUtil = new DBUtil();
		Building build=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select * from building where 1=1");
			ArrayList<Object> param = new ArrayList<Object>();
			
			if(name!=null && !name.trim().isEmpty()) {
				sql.append(" and Building_Name=?");
				param.add(name);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=dbUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int bid=rs.getInt("Building_ID");
				String bname=rs.getString("Building_Name");
				String intro=rs.getString("Building_Introduction");
				build=new Building(bid,bname,intro);
				builds.add(build);
			}
			return builds;
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
	public boolean add(Building build) {
		DBUtil dbUtil = new DBUtil();
		String sql="insert into building(Building_Name,Building_Introduction) value(?,?)";
		Object[] params = {build.getBuilding_Name(),build.getBuilding_Introduction()};
		return dbUtil.executeUpdate(sql, params);
	}
	//检查是否重名
	@Override
	public int Checkname(String name) {
		DBUtil dbUtil = new DBUtil();
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from building where Building_Name=?";
			Object[] params= {name};
			rs=dbUtil.executeQuery(sql, params);
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
			dbUtil.closeAll();
		}
	}
//删除
	@Override
	public boolean del(int id) {
		DBUtil dbUtil = new DBUtil();
		String sql="delete from building where Building_ID=?";
		Object[] params = {id};
		return dbUtil.executeUpdate(sql, params);
	}
	//修改
	@Override
	public boolean update(int id,Building build) {
		DBUtil dbUtil = new DBUtil();
		String sql="update building set Building_Name=?,Building_Introduction=? where Building_ID=?";
		Object[] params= {build.getBuilding_Name(),build.getBuilding_Introduction(),id};
		return dbUtil.executeUpdate(sql, params);
	}
	//根据buildingid查询是否有人居住
	@Override
	public int SelectStu(int id) {
		DBUtil dbUtil = new DBUtil();
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from student,domitory,building where"
					+ " Student_DomitoryID=Domitory_ID and Domitory_BuildingID=Building_ID and Building_ID=?";
			Object[] params= {id};
			rs=dbUtil.executeQuery(sql, params);
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
			dbUtil.closeAll();
		}
	}
	
	
}
