package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Building;

public interface BuildDao {
	//根据id查询building
	public List<Building> QuerySelectById(List<Integer> bs);
	//查询所有building
	public List<Building> QuerySelectAll();
}
