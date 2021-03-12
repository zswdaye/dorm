package com.zsw.dao;

import java.util.List;

import com.zsw.entity.Out;

public interface OutDao {
	public List<Out> QuerySelect();
	public List<Out> QuerySelectPage(int currentPage, int pageSize);
}
