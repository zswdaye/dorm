package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Domitory;

public interface DomitoryDao {
	//根据buildID查询寝室name
	public List<String> QuerySelectBybdID(int id);
	//查询所有寝室
	public List<Domitory> QueryAll(Domitory domit);
	public List<Domitory> QueryAllPage(Domitory domit,int currentPage, int pageSize);
	//检查是否重名
	public int Checkuser(int bid,String name);
	//添加
	public boolean add(Domitory domit);
	//删除
	public boolean del(int id);
	//根据id查询寝室
	public Domitory QuerySelectById(int id);
	//修改
	public boolean update(int id,Domitory domit);
	//根据buildID查询寝室
	public List<Domitory> QueryAllBybdID(int id);
	//根据buildID删除寝室
	public boolean delbybid(int id);
	//根据domiid查询是否有学生正在住
	public int SelectStu(int id);
}
