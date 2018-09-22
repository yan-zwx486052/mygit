package com.zhang.gnifweb.biz.model;

import java.util.List;

/**
 * 
 * @ClassName: PageVo
 * @Description: <返回给页面的分页类>
 * @author 乐
 * @date 2018年9月12日 下午11:58:39
 * 
 * @param <T>
 */
public class PageVo<T>
{

	private Integer total;
	private List<T> rows;

	public Integer getTotal()
	{
		return total;
	}

	public void setTotal(Integer total)
	{
		this.total = total;
	}

	public List<T> getRows()
	{
		return rows;
	}

	public void setRows(List<T> rows)
	{
		this.rows = rows;
	}
}
