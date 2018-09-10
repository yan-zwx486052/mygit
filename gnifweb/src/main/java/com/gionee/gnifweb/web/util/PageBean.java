package com.gionee.gnifweb.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 * @author zhang
 *
 */
public class PageBean<T> {//泛型，消除强制类型转换，安全
	private int page;//第几页
	private int pageSize;//每页记录条数
	private int start;//起始页
	private int totalPage;//总页数
	private int totalCount;//总记录数
	private List<T> list=new ArrayList<T>();//页面要显示的数据
	
	//构造函数
	public PageBean (int page,int pageSize){
		super();
		this.page=page;
		this.pageSize=pageSize;
	}

	
	public List<T> getList() {
		return list;
	}


	public void setList(List<T> list) {
		this.list = list;
	}


	public int getTotalPage() {
		return totalCount%pageSize==0?totalCount/pageSize:(totalCount/pageSize+1);
	}


	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getPage() {
		if(page<=0){
			page=1;//设定默认显示第一页
		}
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStart() {
		return (page-1)*pageSize;//起始（记录数）
	}

	public void setStart(int start) {
		this.start = start;
	}
	
}
