package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.TBDao;
import com.zsw.dao.impl.TBDaoImpl;
import com.zsw.entity.Teacher;
import com.zsw.service.TBService;
import com.zsw.util.DBUtil;

public class TBServiceImpl implements TBService{
	TBDao tbDao=new TBDaoImpl();
	//根据老师id查询楼栋id
	@Override
	public List<Integer> QueryBulidID(int id) {
		return tbDao.QueryBulidID(id);
	}
	//根据buildid删除记录
	public boolean delbybid(int id) {
		return tbDao.delbybid(id);
	}
	//根据bid查询老师id
	public List<Integer> querybybid(int id){
		return tbDao.querybybid(id);
	}
	//根据tids查询老师
	public List<Teacher> QuerySelectById(List<Integer> ts){
		return tbDao.QuerySelectById(ts);
	}
	//添加
	public boolean add(int tid,int bid) {
		return tbDao.add(tid, bid);
	}
	//删除
	public boolean del(int tid,int bid) {
		return tbDao.del(tid, bid);
	}
	//按老师id删除tb
	public boolean delbyteaid(int id) {
		return tbDao.delbyteaid(id);
	}
}
