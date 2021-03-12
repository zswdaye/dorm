package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.DomitoryDao;
import com.zsw.dao.impl.DomitoryDaoImpl;
import com.zsw.entity.Domitory;
import com.zsw.service.DomitoryService;

public class DomitoryServiceImpl implements DomitoryService{
	DomitoryDao domitoryDao=new DomitoryDaoImpl();
	
	//根据buildID查询寝室name
	@Override
	public List<String> QuerySelectBybdID(int id) {
		return domitoryDao.QuerySelectBybdID(id);
	}
	//查询所有寝室
	@Override
	public List<Domitory> QueryAll(Domitory domit) {
		return domitoryDao.QueryAll(domit);
	}

	@Override
	public List<Domitory> QueryAllPage(Domitory domit, int currentPage, int pageSize) {
		return domitoryDao.QueryAllPage(domit, currentPage, pageSize);
	}
	//检查是否重名
	public int Checkuser(int bid,String name) {
		return domitoryDao.Checkuser(bid, name);
	}
	//添加
	public boolean add(Domitory domit) {
		return domitoryDao.add(domit);
	}
	//删除
	public boolean del(int id) {
		return domitoryDao.del(id);
	}
	//根据id查询寝室
	public Domitory QuerySelectById(int id) {
		return domitoryDao.QuerySelectById(id);
	}
	//修改
	public boolean update(int id,Domitory domit) {
		return domitoryDao.update(id, domit);
	}
}
