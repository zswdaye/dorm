package com.zsw.service;

import java.util.List;

import com.zsw.entity.Out;

public interface OutService {
	public List<Out> QuerySelect();
	public List<Out> QuerySelectPage(int currentPage, int pageSize);
	//添加记录
	public boolean add(Out out);
	//按学生id删除outlog
	public boolean delbystuid(int id);
}
