package com.zsw.service.impl;

import java.util.List;

import com.zsw.dao.TBDao;
import com.zsw.dao.impl.TBDaoImpl;
import com.zsw.service.TBService;

public class TBServiceImpl implements TBService{
	TBDao tbDao=new TBDaoImpl();
	//根据老师id查询楼栋id
	@Override
	public List<Integer> QueryBulidID(int id) {
		return tbDao.QueryBulidID(id);
	}

}
