package com.zsw.service;

import com.zsw.entity.Admin;

public interface AdminService {
	public int CheckLogin(String username, String password);
	//查询
	public Admin QuerySelectById(int id);
	//修改
	public boolean Update(int id,Admin admin);
}
