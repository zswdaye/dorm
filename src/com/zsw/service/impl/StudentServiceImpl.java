package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.StudentDao;
import com.zsw.dao.impl.StudentDaoImpl;
import com.zsw.entity.Student;
import com.zsw.service.StudentService;

public class StudentServiceImpl implements StudentService{
	StudentDao studentDao = new StudentDaoImpl();
	@Override
	public int CheckLogin(String username, String password) {
		return studentDao.CheckLogin(username, password);
	}
	@Override
	public Student QuerySelectById(int id) {
		return studentDao.QuerySelectById(id);
	}
	
	@Override
	public Student QuerySelectdoByid(int id) {
		return studentDao.QuerySelectdoByid(id);
	}
	//查询全部
	@Override
	public List<Student> QuerySelectAll(Student stu) {
		return studentDao.QuerySelectAll(stu);
	}
	@Override
	public List<Student> QuerySelectAllPage(Student stu, int currentPage, int pageSize) {
		return studentDao.QuerySelectAllPage(stu, currentPage, pageSize);
	}
	@Override
	public boolean update(int id, Student student) {
		return studentDao.update(id, student);
	}
	//根据buildID查询学生和宿舍
	@Override
	public List<Student> QuerySelectBybdId(int id, Student stu) {
		return studentDao.QuerySelectBybdId(id, stu);
	}
	@Override
	public List<Student> QuerySelectBybdPage(int id, Student stu, int currentPage, int pageSize) {
		return studentDao.QuerySelectBybdPage(id, stu, currentPage, pageSize);
	}
	//检查是否有相同的学号
	@Override
	public int Checkuser(String username) {
		return studentDao.Checkuser(username);
	}
	//添加
	@Override
	public boolean add(Student stu) {
		return studentDao.add(stu);
	}
	//删除
	@Override
	public boolean del(int id) {
		return studentDao.del(id);
	}

}
