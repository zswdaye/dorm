package com.zsw.service;

import java.util.List;

import com.zsw.entity.Log;
import com.zsw.entity.Student;

public interface LogService {
	//按学生学号查询
	public List<Log> QuerySelectById(int id);
	public List<Log> QuerySelectByPage(int id,int currentPage, int pageSize);
	//按楼栋id查询
	public List<Log> QuerySelectbdById(int id,Student stu);
	public List<Log> QuerySelectbdByPage(int id,Student stu,int currentPage, int pageSize);
	//插入缺寝记录
	public boolean addLog(Log log);
	//检查同学是否是同一天登记
	public int Checkdate(int id,String date);
	//按学生id删除log
	public boolean delbystuid(int id);
	//按老师id删除log
	public boolean delbyteaid(int id);
}
