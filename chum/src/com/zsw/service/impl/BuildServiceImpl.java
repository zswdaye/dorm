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
	//根据id查询单个building
	public Building queryselect(int id) {
		return buildDao.queryselect(id);
	}
	//根据名称查询所有building
	public List<Building> QuerySelectByName(String name){
		return buildDao.QuerySelectByName(name);
	}
	public List<Building> QuerySelectByNamePage(String name,int currentPage, int pageSize){
		return buildDao.QuerySelectByNamePage(name, currentPage, pageSize);
	}
	//添加
	public boolean add(Building build) {
		return buildDao.add(build);
	}
	//检查是否重名
	public int Checkname(String name) {
		return buildDao.Checkname(name);
	}
	//删除
	public boolean del(int id) {
		return buildDao.del(id);
	}
	//修改
	public boolean update(int id,Building build) {
		return buildDao.update(id, build);
	}
	//根据buildingid查询是否有人居住
	public int SelectStu(int id) {
		return buildDao.SelectStu(id);
	}
}
