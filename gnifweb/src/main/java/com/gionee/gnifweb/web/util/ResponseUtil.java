package com.gionee.gnifweb.web.util;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/**
 * 
 * @ClassName: ResponseUtil
 * @Description: <返回json数据的工具类>
 * @author 乐
 * @date 2018年9月12日 下午11:53:47
 *
 */
public class ResponseUtil
{
	public static void write(HttpServletResponse res, Object obj) throws Exception
	{
		res.setContentType("text/html;charset=UTF-8");// 设定返回数据编码为utf-8
		PrintWriter out = res.getWriter();
		out.println(obj);// 数据输出到前台页面
		out.flush();// 清空缓冲区
		out.close();// 关闭输出流
	}
}
