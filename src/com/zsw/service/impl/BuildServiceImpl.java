package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.BuildDao;
import com.zsw.dao.impl.BuildDaoImpl;
import com.zsw.entity.Building;
import com.zsw.service.BuildService;

public class BuildServiceImpl implements BuildService{
	BuildDao buildDao=new BuildDaoImpl();
	//根据id查询building
	@Override
	public List<Building> QuerySelectById(List<Integer> bs) {
		return buildDao.QuerySelectById(bs);
	}
	//查询所有building
	@Override
	public List<Building> QuerySelectAll() {
		return buildDao.QuerySelectAll();
	}
	
}
