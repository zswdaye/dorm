package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Teacher;

public interface TeacherDao {
	public int CheckLogin(String username, String password);
	//检查是否有相同的用户名
	public int Checkuser(String username);
	//查询
	public Teacher QuerySelectById(int id);
	//查询全部
	public List<Teacher> QuerySelectAll(Teacher tea);
	public List<Teacher> QuerySelectAllPage(Teacher tea,int currentPage, int pageSize);
	//修改
	public boolean update(int id,Teacher teacher);
	//添加
	public boolean add(Teacher tea);
	//删除
	public boolean del(int id);
	//查询全部老师
	public List<Teacher> QuerySelectAlltea();
}
