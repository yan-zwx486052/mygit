package com.zhang.gnifweb.web.util;

/**
 * 
 * @ClassName: StringUtil
 * @Description: TODO(字符串工具类)
 * @author 乐
 * @date 2018年9月12日 下午10:54:22
 *
 */
public class StringUtil
{
	/**
	 * 
	 * @Title: isEmpty
	 * @Description: <判空>
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str)
	{
		if (str == null || "".equals(str))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 
	 * @Title: isNotEmpty
	 * @Description: <判断是否不为空>
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str)
	{
		if (str != null && !"".equals(str))
		{
			return true;
		} else
		{
			return false;
		}
	}

	/**
	 * 
	 * @Title: formatLike
	 * @Description: <格式化模糊查询>
	 * @param str
	 * @return
	 */
	public static String formatLike(String str)
	{
		if (isNotEmpty(str))
		{
			return "%" + str + "%";
		} else
		{
			return null;
		}
	}
}
