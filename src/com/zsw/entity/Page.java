package com.zsw.entity;

import java.util.List;


public class Page {
	//当前页
	private int currentPage;
	//页面大小
	private int pageSize;
	//总数据
	private int totalCount;
	//总页数
	private int totalPage;
	//当前页的数据集合 students
	private List<Object> object;
	
	
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Page(int currentPage, int pageSize, int totalCount, int totalPage, List<Object> object) {
		super();
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.object = object;
	}

	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	//要注意顺序，先设总数据再设页面大小
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		this.totalPage=this.totalCount%this.pageSize ==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
//	public void setTotalPage(int totalPage) {
//		this.totalPage = totalPage;
//	}

	public List<Object> getObject() {
		return object;
	}

	public void setObject(List<Object> object) {
		this.object = object;
	}
	
}
