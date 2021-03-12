package com.zsw.service;

import java.util.List;

import com.zsw.entity.Student;

public interface StudentService {
	public int CheckLogin(String username, String password);
	//检查是否有相同的学号
	public int Checkuser(String username);
	//查询
	public Student QuerySelectById(int id);
	//查询全部
	public List<Student> QuerySelectAll(Student stu);
	public List<Student> QuerySelectAllPage(Student stu,int currentPage, int pageSize);
	//查询包含宿舍名
	public Student QuerySelectdoByid(int id);
	//修改
	public boolean update(int id,Student student);
	//根据buildID查询学生和宿舍
	public List<Student> QuerySelectBybdId(int id,Student stu);
	public List<Student> QuerySelectBybdPage(int id,Student stu,int currentPage, int pageSize);
	//添加
	public boolean add(Student stu);
	//删除
	public boolean del(int id);
}
