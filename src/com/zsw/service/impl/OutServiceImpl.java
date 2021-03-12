package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.OutDao;
import com.zsw.dao.impl.OutDaoImpl;
import com.zsw.entity.Out;
import com.zsw.service.OutService;

public class OutServiceImpl implements OutService{
	OutDao outDao=new OutDaoImpl();
	@Override
	public List<Out> QuerySelect() {
		return outDao.QuerySelect();
	}

	@Override
	public List<Out> QuerySelectPage(int currentPage, int pageSize) {
		return outDao.QuerySelectPage(currentPage, pageSize);
	}

}
