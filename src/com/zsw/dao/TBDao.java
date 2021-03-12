package com.zsw.dao;

import java.util.List;

public interface TBDao {
	//根据老师id查询楼栋id
	public List<Integer> QueryBulidID(int id);
}
