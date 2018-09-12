package com.gionee.gnifweb.biz.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @ClassName: QueryMap
 * @Description: <一句話功能簡述>
 * @author 乐
 * @date 2018年9月12日 下午11:58:50
 *
 */
public class QueryMap
{
	private Map<String, Object> map = new HashMap<String, Object>();

	public QueryMap()
	{
	}

	public void put(String key, Object value)
	{
		this.map.put(key, value);
	}

	public Map<String, Object> getMap()
	{
		return this.map;
	}
}
