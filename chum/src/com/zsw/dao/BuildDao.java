package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Building;

public interface BuildDao {
	//根据id查询building
	public List<Building> QuerySelectById(List<Integer> bs);
	//查询所有building
	public List<Building> QuerySelectAll();
	//根据id查询单个building
	public Building queryselect(int id);
	//根据名称查询所有building
	public List<Building> QuerySelectByName(String name);
	public List<Building> QuerySelectByNamePage(String name,int currentPage, int pageSize);
	//添加
	public boolean add(Building build);
	//检查是否重名
	public int Checkname(String name);
	//删除
	public boolean del(int id);
	//修改
	public boolean update(int id,Building build);
	//根据buildingid查询是否有人居住
	public int SelectStu(int id);
}
