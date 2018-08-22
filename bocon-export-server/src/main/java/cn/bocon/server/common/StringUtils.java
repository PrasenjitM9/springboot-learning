package cn.bocon.server.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作字符串工具类
 * 版权所有：2017-广州博控自动化技术
 * 项目名称：common_api
 * 类描述：
 * 类名称：cn.bocon.pmpcms.common.StringUtil
 * 创建人：范志超
 * 创建时间：2017-7-26 下午2:03:51
 * 修改人：
 * 修改时间：2017-7-26 下午2:03:51
 * 修改备注：
 * @version V1.0
 */
public class StringUtils {
	
	/**
	 * 替换空字符
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceEmpty(String src) {
		if (null == src || "".equals(src))
			return "";
		else {
			if ("NULL".equals(src.trim().toUpperCase()))
				return "";
			return src.trim();
		}

	}
	
	/**
	 * 
	* @Title: isNumeric
	* @Description: TODO(判断字符串是否存在数字)
	* @param str
	* @return
	 */
	public static boolean isNumeric(String str){ 
		   Pattern pattern = Pattern.compile("[0-9]*"); 
		   Matcher isNum = pattern.matcher(str);
		   if( !isNum.matches() ){
		       return false; 
		   } 
		   return true; 
		}
	
	/**
	 * 自定义截取字符串
	 * @param keqflow    	AAbbbCC
	 * @param start			AA
	 * @param end			CC
	 * @return				bbb
	 */
	public static  String mySubString(String keqflow, String start, String end) {
		String sub = keqflow.split(start)[1];
		int index = sub.indexOf(end);
		return sub.substring(0, index);
	}

	/**
	 * 自定义截取字符串
	 * @param keqflow    	AAbbbCC
	 * @param start			bbb
	 * @return				CC
	 */
	public static String mySubString(String keqflow, String start) {
		return keqflow.split(start)[1];
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (null == s || "".equals(s.trim()) || "null".equalsIgnoreCase(s)) {
			return true;
		} else {
			return false;
		}
	}	
	
	/**
	 * 不为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
