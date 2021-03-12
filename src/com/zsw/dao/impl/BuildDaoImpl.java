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
			rs=DBUtil.executeQuery(sql.toString(), params);
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}
	//查询所有building
	@Override
	public List<Building> QuerySelectAll() {
		List<Building> builds=new ArrayList<>();
		Building build=null;
		ResultSet rs =null;
		try {
			String sql ="select * from building";		
			rs=DBUtil.executeQuery(sql, null);
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
	}

}
