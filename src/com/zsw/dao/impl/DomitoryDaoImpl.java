package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.DomitoryDao;
import com.zsw.entity.Building;
import com.zsw.entity.Domitory;
import com.zsw.entity.Log;
import com.zsw.entity.Student;
import com.zsw.util.DBUtil;

public class DomitoryDaoImpl implements DomitoryDao{
	//根据buildID查询寝室name
	@Override
	public List<String> QuerySelectBybdID(int id) {
		List<String> dnames=new ArrayList<>();
		String dname=null;
		ResultSet rs =null;
		try {
			String sql ="select Domitory_Name from domitory where Domitory_BuildingID=?";			
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
			while(rs.next()) {
				dname=rs.getString("Domitory_Name");
				dnames.add(dname);
			}
			return dnames;
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
	//查询所有寝室
	@Override
	public List<Domitory> QueryAll(Domitory domit) {
		List<Domitory> domis=new ArrayList<>();
		Domitory domi=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select d.Domitory_ID,d.Domitory_Name,d.Domitory_Type,d.Domitory_Number,d.Domitory_Tel,b.Building_Name from"
					+ " domitory d,building b where b.Building_ID=d.Domitory_BuildingID");
			ArrayList<Object> param = new ArrayList<Object>();
			int buid=domit.getDomitory_BuildingID();
			if(buid!=0) {
				sql.append(" and d.Domitory_BuildingID=?");
				param.add(buid);
			}
			String dname=domit.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String dtel=domit.getDomitory_Tel();
			if(dtel!=null && !dtel.trim().isEmpty()) {
				sql.append(" and d.Domitory_Tel=?");
				param.add(dtel);
			}
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int did=rs.getInt("Domitory_ID");
				String doname=rs.getString("Domitory_Name");
				String dtype=rs.getString("Domitory_Type");
				String dnum=rs.getString("Domitory_Number");
				String dotel=rs.getString("Domitory_Tel");
				String buname=rs.getString("Building_Name");
				domi=new Domitory(did,doname,dtype,dnum,dotel,buname);
				domis.add(domi);
			}
			return domis;
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
	public List<Domitory> QueryAllPage(Domitory domit, int currentPage, int pageSize) {
		List<Domitory> domis=new ArrayList<>();
		Domitory domi=null;
		ResultSet rs =null;
		try {
			StringBuilder sql =new StringBuilder("select d.Domitory_ID,d.Domitory_Name,d.Domitory_Type,d.Domitory_Number,d.Domitory_Tel,b.Building_Name from"
					+ " domitory d,building b where b.Building_ID=d.Domitory_BuildingID");
			ArrayList<Object> param = new ArrayList<Object>();
			int buid=domit.getDomitory_BuildingID();
			if(buid!=0) {
				sql.append(" and d.Domitory_BuildingID=?");
				param.add(buid);
			}
			String dname=domit.getDomitory_Name();
			if(dname!=null && !dname.trim().isEmpty()) {
				sql.append(" and d.Domitory_Name=?");
				param.add(dname);
			}
			String dtel=domit.getDomitory_Tel();
			if(dtel!=null && !dtel.trim().isEmpty()) {
				sql.append(" and d.Domitory_Tel=?");
				param.add(dtel);
			}
			sql.append(" limit ?,?");
			param.add((currentPage-1)*pageSize);
			param.add(pageSize);
			Object[] params=param.toArray();
			rs=DBUtil.executeQuery(sql.toString(), params);
			while(rs.next()) {
				int did=rs.getInt("Domitory_ID");
				String doname=rs.getString("Domitory_Name");
				String dtype=rs.getString("Domitory_Type");
				String dnum=rs.getString("Domitory_Number");
				String dotel=rs.getString("Domitory_Tel");
				String buname=rs.getString("Building_Name");
				domi=new Domitory(did,doname,dtype,dnum,dotel,buname);
				domis.add(domi);
			}
			return domis;
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
	//检查是否重名
	@Override
	public int Checkuser(int bid,String name) {
		ResultSet rs =null;
	    int num=0;
		try {
			String sql="select count(*) from domitory where Domitory_BuildingID=? and Domitory_Name=?";
			Object[] params= {bid,name};
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
	public boolean add(Domitory domit) {
		String sql="insert into domitory(Domitory_BuildingID,Domitory_Name,Domitory_Type,Domitory_Number,Domitory_Tel) value(?,?,?,?,?)";
		Object[] params = {domit.getDomitory_BuildingID(),domit.getDomitory_Name(),domit.getDomitory_Type(),domit.getDomitory_Number(),domit.getDomitory_Tel()};
		return DBUtil.executeUpdate(sql, params);
	}
	//删除
	@Override
	public boolean del(int id) {
		String sql="delete from domitory where Domitory_ID=?";
		Object[] params = {id};
		return DBUtil.executeUpdate(sql, params);
	}
	//根据id查询寝室
	@Override
	public Domitory QuerySelectById(int id) {
		Domitory domi=null;
		ResultSet rs =null;
	    
		try {
			String sql="select * from domitory where Domitory_ID=?";
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
			if(rs.next()) {
				int no=rs.getInt("Domitory_ID");
				int bid=rs.getInt("Domitory_BuildingID");
				String name=rs.getString("Domitory_Name");
				String type=rs.getString("Domitory_Type");
				String number=rs.getString("Domitory_Number");
				String tel=rs.getString("Domitory_Tel");
				domi=new Domitory(no,bid,name,type,number,tel);
			}
			return domi;
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
	public boolean update(int id, Domitory domit) {
		String sql="update domitory set Domitory_BuildingID=?,Domitory_Name=?,Domitory_Type=?,Domitory_Number=?,Domitory_Tel=? where Domitory_ID=?";
		Object[] params= {domit.getDomitory_BuildingID(),domit.getDomitory_Name(),domit.getDomitory_Type(),domit.getDomitory_Number(),domit.getDomitory_Tel(),id};
		return DBUtil.executeUpdate(sql, params);
	}
	

}
