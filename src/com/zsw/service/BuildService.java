package com.zsw.service;

import java.util.List;

import com.zsw.entity.Building;

public interface BuildService {
	//根据id查询building
	public List<Building> QuerySelectById(List<Integer> bs);
	//查询所有building
	public List<Building> QuerySelectAll();
}
