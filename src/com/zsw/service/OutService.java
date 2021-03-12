package com.zsw.service;

import java.util.List;

import com.zsw.entity.Out;

public interface OutService {
	public List<Out> QuerySelect();
	public List<Out> QuerySelectPage(int currentPage, int pageSize);
}
