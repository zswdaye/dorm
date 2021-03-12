package com.zsw.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zsw.dao.TBDao;
import com.zsw.entity.Log;
import com.zsw.util.DBUtil;

public class TBDaoImpl implements TBDao{
	//根据老师id查询楼栋id
	@Override
	public List<Integer> QueryBulidID(int id) {
		List<Integer> bs=new ArrayList<>();
		int build;
		ResultSet rs =null;
		try {
			String sql="select * from tb where TB_TeacherID=?";
			Object[] params= {id};
			rs=DBUtil.executeQuery(sql, params);
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
			DBUtil.closeAll(rs, DBUtil.pstmt, DBUtil.connection);
		}
		
	}

}
