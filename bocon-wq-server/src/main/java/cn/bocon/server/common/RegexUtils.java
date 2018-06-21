package cn.bocon.server.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	/**
	 * 
	* @Title: getString
	* @Description: 匹配字符串
	* @param string
	* @param regex
	* @return
	 */
	public static String getString(String string, String regex) {
		String result = "";
		// 将字符串编译为正则表达式的对象表示形式                                   
		Pattern pattern = Pattern.compile(regex);                                     
		// 创建对字符串 string 根据正则表达式 pattern 进行匹配操作的匹配器对象      
		Matcher matcher = pattern.matcher(string);                                  
		// 查找下一个匹配的字符串内容，如果找到返回 true，找不到返回 false          
		if(matcher.find()) {                                                     
		    // 输出捕获到的匹配内容                                                 
		    result = matcher.group(1);
		} 	
		return result;
	}

}
