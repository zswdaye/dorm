package com.zsw.service.impl;

import com.zsw.dao.AdminDao;
import com.zsw.dao.impl.AdminDaoImpl;
import com.zsw.entity.Admin;
import com.zsw.service.AdminService;

public class AdminServiceImpl implements AdminService{
	AdminDao adminDao = new AdminDaoImpl();
	@Override
	public int CheckLogin(String username, String password) {		
		return adminDao.CheckLogin(username, password);
	}

	@Override
	public Admin QuerySelectById(int id) {		
		return adminDao.QuerySelectById(id);
	}

	@Override
	public boolean Update(int id,Admin admin) {
		return adminDao.Update(id,admin);		
	}

}
