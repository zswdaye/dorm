package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.TeacherDao;
import com.zsw.dao.impl.TeacherDaoImpl;
import com.zsw.entity.Teacher;
import com.zsw.service.TeacherService;

public class TeacherServiceImpl implements TeacherService{
	TeacherDao teacherDao=new TeacherDaoImpl();

	@Override
	public int CheckLogin(String username, String password) {
		return teacherDao.CheckLogin(username, password);
	}
	//查询
	@Override
	public Teacher QuerySelectById(int id) {
		return teacherDao.QuerySelectById(id);
	}
	//查询全部
	@Override
	public List<Teacher> QuerySelectAll(Teacher tea) {
		return teacherDao.QuerySelectAll(tea);
	}
	@Override
	public List<Teacher> QuerySelectAllPage(Teacher tea, int currentPage, int pageSize) {
		return teacherDao.QuerySelectAllPage(tea, currentPage, pageSize);
	}
	//修改
	@Override
	public boolean update(int id, Teacher teacher) {
		return teacherDao.update(id, teacher);
	}
	//检查是否有相同的用户名
	@Override
	public int Checkuser(String username) {
		return teacherDao.Checkuser(username);
	}
	@Override
	public boolean add(Teacher tea) {
		return teacherDao.add(tea);
	}
	//	//删除
	@Override
	public boolean del(int id) {
		return teacherDao.del(id);
	}
	
	
}
