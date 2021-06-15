package com.zsw.service;

import java.util.List;

import com.zsw.entity.Teacher;

public interface TBService {
	//根据老师id查询楼栋id
	public List<Integer> QueryBulidID(int id);
	//根据buildid删除记录
	public boolean delbybid(int id);
	//根据bid查询老师id
	public List<Integer> querybybid(int id);
	//根据tids查询老师
	public List<Teacher> QuerySelectById(List<Integer> ts);
	//添加
	public boolean add(int tid,int bid);
	//删除
	public boolean del(int tid,int bid);
	//按老师id删除tb
	public boolean delbyteaid(int id);
}
