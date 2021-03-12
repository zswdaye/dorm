package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Log;
import com.zsw.entity.Student;

public interface LogDao {
	//按学生学号查询
	public List<Log> QuerySelectById(int id);
	public List<Log> QuerySelectByPage(int id,int currentPage, int pageSize);
	//按楼栋id查询
	public List<Log> QuerySelectbdById(int id,Student stu);
	public List<Log> QuerySelectbdByPage(int id,Student stu,int currentPage, int pageSize);
	//插入缺寝记录
	public boolean addLog(Log log);
}
