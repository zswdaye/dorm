package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.LogDao;
import com.zsw.dao.impl.LogDaoImpl;
import com.zsw.entity.Log;
import com.zsw.entity.Student;
import com.zsw.service.LogService;

public class LogServiceImpl implements LogService{
	LogDao logDao=new LogDaoImpl();
	//学生按学号查询
	@Override
	public List<Log> QuerySelectById(int id) {
		return logDao.QuerySelectById(id);
	}
	@Override
	public List<Log> QuerySelectByPage(int id, int currentPage, int pageSize) {
		return logDao.QuerySelectByPage(id, currentPage, pageSize);
	}
	//按楼栋id查询
	@Override
	public List<Log> QuerySelectbdById(int id, Student stu) {
		return logDao.QuerySelectbdById(id, stu);
	}
	@Override
	public List<Log> QuerySelectbdByPage(int id, Student stu, int currentPage, int pageSize) {
		return logDao.QuerySelectbdByPage(id, stu, currentPage, pageSize);
	}
	//插入缺寝记录
	@Override
	public boolean addLog(Log log) {
		return logDao.addLog(log);
	}
	//检查同学是否是同一天登记
	public int Checkdate(int id,String date) {
		return logDao.Checkdate(id, date);
	}
	//按学生id删除log
	public boolean delbystuid(int id) {
		return logDao.delbystuid(id);
	}
	//按老师id删除log
	public boolean delbyteaid(int id) {
		return logDao.delbyteaid(id);
	}
}
