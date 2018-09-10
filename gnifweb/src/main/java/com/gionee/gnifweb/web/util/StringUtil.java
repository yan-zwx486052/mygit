package com.gionee.gnifweb.web.util;

/**
 * 字符串工具类
 * 
 * @author zhang
 *
 */
public class StringUtil {
	/**
	 * 判断是否为空
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断是否不为空
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 格式化模糊查询
	 */
	public static String formatLike(String str) {
		if (isNotEmpty(str)) {
			return "%" + str + "%";
		} else {
			return null;
		}
	}
	
	public static void getValue() {
		
	}
}
