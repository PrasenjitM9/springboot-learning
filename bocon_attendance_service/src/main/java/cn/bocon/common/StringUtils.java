package cn.bocon.common;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符处理工具类
 * 
 */
public class StringUtils {

	public static final String	EMPTY			= "";

	private static final int	INDEX_NOT_FOUND	= -1;

	/**
	 * 定义私有构造函数,使其不可实例化和子类化
	 * 
	 */
	private StringUtils() {

	}

	/**
	 * 替换空字符
	 * 
	 * @param src
	 * @return
	 */
	public static String replaceEmpty(String src) {
		if (null == src || "".equals(src))
			return "";
		else{
			if("NULL".equals(src.trim().toUpperCase()))
				return "";
			return src.trim();
		}
			
	}

	/**
	 * 中文编码
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String encode(String src) throws Exception {
		if (null == src || src.equals(""))
			return "";
		else
			return new String(src.getBytes("ISO8859-1"), "GBK");
	}

	/**
	 * 参数编码
	 * 
	 * @param src
	 * @return
	 * @throws Exception
	 */
	public static String encodeParam(String src) throws Exception {
			return new String(src.getBytes("ISO-8859-1"), "utf-8");
	}
	/**
	 * 字符串是否为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.length() == 0 || value.trim().equals("");
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

	/**
	 * 判断字符串是否为空或空白对象
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		int strLen = 0;
		if (null == value || (strLen = value.length()) == 0) {
			return true;
		}

		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(value.charAt(i)) == false) {
				return false;
			}
		}

		return true;
	}

	/**
	 * 去除空格
	 * 
	 * @param value
	 * @return null / string
	 */
	public static String trim(String value) {
		return (null == value) ? null : value.trim();
	}

	/**
	 * 为空或空白时 返回 null 反之 string.trim()
	 * 
	 * @param value
	 * @return null / string
	 */
	public static String trimToNull(String value) {
		return isEmpty(trim(value)) ? null : value.trim();
	}

	/**
	 * 为空或空白时 返回 "" 反之 string.trim()
	 * 
	 * @param value
	 * @return
	 */
	public static String trimToEmpty(String value) {
		return isEmpty(trim(value)) ? EMPTY : value.trim();
	}

	/**
	 * 去除左边空白
	 * 
	 * @param value
	 * @return
	 * 
	 * <pre>
	 * StringUtils.removeLeftBlank(&quot;  \n\thahahaha&quot;)  = &quot;hahahaha&quot;
	 * StringUtils.removeLeftBlank(&quot;  \n\t&quot;)          = &quot;&quot;
	 * </pre>
	 */
	public static String removeLeftBlank(String value) {
		int strLen = 0;

		int start = 0;

		if (null == value || (strLen = value.length()) == 0) {
			return value;
		}

		while ((start < strLen) && (Character.isWhitespace(value.charAt(start)))) {
			start++;
		}

		return value.substring(start);
	}

	/**
	 * 去除右边空白
	 * 
	 * @param value
	 * @return
	 */
	public static String removeRightBlank(String value) {
		int strLen = 0;

		if (null == value || (strLen = value.length()) == 0) {
			return value;
		}

		while ((strLen > 0) && (Character.isWhitespace(value.charAt(strLen - 1)))) {
			strLen--;
		}

		return value.substring(0, strLen);
	}

	/**
	 * 查找字符
	 * 
	 * @param value
	 * @param searchChar
	 * @return
	 */
	public static int indexOf(String value, char searchChar) {
		if (isEmpty(value))
			return INDEX_NOT_FOUND;

		return value.indexOf(searchChar);
	}

	/**
	 * 查找字符
	 * 
	 * @param value
	 * @param searchChar
	 * @param startPos
	 *            开始下标
	 * @return
	 */
	public static int indexOf(String value, char searchChar, int startPos) {
		if (isEmpty(value))
			return INDEX_NOT_FOUND;

		return value.indexOf(searchChar, startPos);
	}

	/**
	 * 查找字符串
	 * 
	 * @param value
	 * @param searchString
	 * @return
	 */
	public static int indexOf(String value, String searchString) {
		if (null == value || null == searchString)
			return INDEX_NOT_FOUND;

		return value.indexOf(searchString);
	}

	/**
	 * 查找字符串
	 * 
	 * @param value
	 * @param searchString
	 * @param startPos
	 *            开始下标
	 * @return
	 */
	public static int indexOf(String value, String searchString, int startPos) {
		if (null == value || null == searchString || startPos < 0)
			return INDEX_NOT_FOUND;

		return value.indexOf(searchString, startPos);
	}

	/**
	 * 查询指定字符出现次数的下标 从0开始
	 * 
	 * @param value
	 * @param searchChar
	 * @param orderIndex
	 *            出现次数
	 * @return
	 */
	public static int orderIndexOf(String value, char searchChar, int orderIndex) {
		if (null == value || orderIndex < 0) {
			return INDEX_NOT_FOUND;
		}

		int index = INDEX_NOT_FOUND;

		int found = 0;
		do {
			index = value.indexOf(searchChar, index + 1);
			if (index < 0)
				return index;

			found++;

		} while (found < orderIndex);

		return index;
	}

	public static int orderIndexOf(String value, String searchString, int orderIndex) {
		if (null == value || null == searchString || orderIndex < 0) {
			return INDEX_NOT_FOUND;
		}

		int index = INDEX_NOT_FOUND;

		int found = 0;
		do {
			index = value.indexOf(searchString, index + 1);
			if (index < 0)
				return index;

			found++;

		} while (found < orderIndex);

		return index;
	}

	/**
	 * 字符串是否包含制定字符
	 * 
	 * @param value
	 * @param containChar
	 * @return
	 */
	public static boolean contains(String value, char containChar) {
		if (isEmpty(value))
			return false;

		return value.indexOf(containChar) >= 0 ? true : false;
	}

	/**
	 * 字符串是否包含指定子字符串--区分大小写
	 * 
	 * @param value
	 * @param containString
	 * @return
	 */
	public static boolean contains(String value, String containString) {
		return StringUtils.indexOf(value, containString) >= 0 ? false : true;
	}

	/**
	 * 字符串是否包含指定子字符串--不区分大小写
	 * 
	 * @param value
	 * @param containString
	 * @return
	 */
	public static boolean containsIgnoreCase(String value, String containString) {
		return StringUtils.contains(value.toLowerCase(), containString.toLowerCase());
	}

	/**
	 * 对Unicode进行转码 eg:StringUtils.decodeUnicode("\\u7777\\u8765")=睷蝥
	 * 
	 * @param value
	 * @return
	 */
	public static String decodeUnicode(String value) {
		if (null == value) {
			return "";
		}

		StringBuffer result = new StringBuffer("");

		StringTokenizer stk = new StringTokenizer(value, "\\u");

		char c;

		for (; stk.hasMoreTokens(); result.append(c)) {
			String ii = stk.nextToken();
			c = (char) Integer.parseInt(ii, 16);
		}

		return result.toString();
	}

	/**
	 * 替换字符串中指定的子字符串
	 * 
	 * @param srcData
	 * @param src
	 * @param patten
	 * @return
	 */
	public static String changeToString(String srcData, String src, String patten) {
		int nLen = 0;
		int nSrcLen = src.length();

		int nPos = srcData.indexOf(src);

		while (nPos > 0) {
			nLen = srcData.length();
			srcData = srcData.substring(0, nPos) + patten + (nLen > nPos ? (srcData.substring(nPos + nSrcLen, nLen)) : "");
			nPos = srcData.indexOf(src);
		}

		return srcData;
	}

	/**
	 * 字符串格式化，对回车符号进行HTML标签替换
	 * 
	 * @param srcData
	 *            需格式化的字符串
	 * @return 已格式化的字符串
	 */
	public static String getEnterLine(String srcData) {
		int nLen = 0;
		int nPos = srcData.indexOf('\r');

		while (nPos > 0) {
			nLen = srcData.length();
			srcData = srcData.substring(0, nPos) + "<br>" + (nLen > nPos ? srcData.substring(nPos + 1, nLen) : "");
			nPos = srcData.indexOf('\r');
		}

		return srcData;
	}

	/**
	 * 去掉字符串中的回车换行符
	 * 
	 * @param srcData
	 *            需去掉回车换行符的字符串
	 * @return 去掉回车换行符的字符串
	 */
	public static String filterSpace(String srcData) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < srcData.length(); i++) {
			int asc = srcData.charAt(i);

			if (asc != 13 && asc != 10)
				sb.append(srcData.subSequence(i, i + 1));
		}
		return sb.toString();
	}

	/**
	 * 替换字符串中的双引号为HTML中引号表示标签
	 * 
	 * @param srcData
	 *            需替换的字符串
	 * @return 替换后的字符串
	 */
	public static String changeHtmlQuotSign(String srcData) {
		int nLen = 0;
		int nPos = srcData.indexOf('\"');

		while (nPos > 0) {
			nLen = srcData.length();
			srcData = srcData.substring(0, nPos) + "&quot;" + (nLen > nPos ? srcData.substring(nPos + 1, nLen) : "");
			nPos = srcData.indexOf('\"');
		}
		return srcData;

	}

	/**
	 * 单引号转换为双引号
	 * 
	 * @param srcData
	 *            需替换的字符串
	 * @return 替换后的字符串
	 */
	public static String changeInvertedComma(String srcData) {
		int nLen = 0;
		int nPos = srcData.indexOf('\'');

		while (nPos > 0) {
			nLen = srcData.length();
			srcData = srcData.substring(0, nPos) + "\"" + (nLen > nPos ? srcData.substring(nPos + 1, nLen) : "");
			nPos = srcData.indexOf('\'');
		}
		return srcData;
	}

	/**
	 * 首字母大写
	 * 
	 * @param param
	 * @return
	 */
	public static String upperCaseFirstWord(String param) {
		return param.substring(0, 1).toUpperCase() + param.substring(1);
	}

	/**
	 * 从身份证号提取出生日期
	 * 
	 * @param idCard
	 * @return
	 */
	public static String idCardtoBirthday(String idCard) {
		if (StringUtils.isEmpty(idCard))
			return "";
		idCard = StringUtils.replaceEmpty(idCard);
		if (idCard.length() == 15) {
			return "19" + idCard.substring(6, 8) + "-" + idCard.substring(8, 10) + "-" + idCard.substring(10, 12);
		} else if (idCard.length() == 18) {
			return idCard.substring(6, 10) + "-" + idCard.substring(10, 12) + "-" + idCard.substring(12, 14);
		} else {
			return "";
		}
	}

	/**
	 * 从字符串中提取数字部分
	 * 
	 * @return
	 */
	public static String stringToNumericPortion(String src) {
		StringBuffer result = new StringBuffer("");
		Pattern pattern = Pattern.compile("[^0-9]");
		String[] temp = pattern.split(src);
		for (String item : temp) {
			if (!"".equals(item.trim()))
				result.append(item.trim());
		}
		return result.toString();
	}

	/**
	 * 货币金额小写转大写
	 * 
	 * @param money
	 *            货币金额
	 * @return
	 */
	public static String moneyLToU(String money) {
		String result = "";
		StringBuffer item = new StringBuffer("");
		int index = 1;
		int len = 0;
		if (null == money)
			throw new RuntimeException("The input param is null");

		money = money.trim();
		if ("".equals(money))
			throw new java.lang.RuntimeException("The input param is empty");

		try {
			Float.parseFloat(money);
		} catch (NumberFormatException ex) {
			throw ex;
		}

		if (money.indexOf(".") >= 0 && money.length() - 1 >= money.indexOf(".") + 2)
			money = money.substring(0, money.indexOf(".") + 3);
		else if (money.indexOf(".") >= 0 && money.length() - 1 < money.indexOf(".") + 2)
			money = money + "0";
		else
			money = money + ".00";

		len = money.length();
		for (; index <= len; index++) {
			switch (money.substring(len - index, len - index + 1).charAt(0)) {
				case '.':
					item.append("元");
					break;
				case '0':
					item.append("零");
					break;
				case '1':
					item.append("壹");
					break;
				case '2':
					item.append("贰");
					break;
				case '3':
					item.append("叁");
					break;
				case '4':
					item.append("肆");
					break;
				case '5':
					item.append("伍");
					break;
				case '6':
					item.append("陆");
					break;
				case '7':
					item.append("柒");
					break;
				case '8':
					item.append("捌");
					break;
				case '9':
					item.append("玖");
					break;
			}

			switch (index) {
				case 1:
					item.append("分");
					break;
				case 2:
					item.append("角");
					break;
				case 3:
					item.append("");
					break;
				case 4:
					item.append("");
					break;
				case 5:
					item.append("拾");
					break;
				case 6:
					item.append("佰");
					break;
				case 7:
					item.append("仟");
					break;
				case 8:
					item.append("万");
					break;
				case 9:
					item.append("拾");
					break;
				case 10:
					item.append("佰");
					break;
				case 11:
					item.append("仟");
					break;
				case 12:
					item.append("亿");
					break;
				case 13:
					item.append("拾");
					break;
				case 14:
					item.append("佰");
					break;
				case 15:
					item.append("仟");
					break;
				case 16:
					item.append("万");
					break;
			}
			result = item.toString() + result;
			item.delete(0, item.length());
		}
		result = result.replaceAll("零拾", "零");
		result = result.replaceAll("零佰", "零");
		result = result.replaceAll("零仟", "零");
		result = result.replaceAll("零零零", "零");
		result = result.replaceAll("零零", "零");
		result = result.replaceAll("零角零分", "整");
		result = result.replaceAll("零分", "整");
		result = result.replaceAll("零角", "零");
		result = result.replaceAll("零亿零万零元", "亿元");
		result = result.replaceAll("亿零万零元", "亿元");
		result = result.replaceAll("零亿零万", "亿");
		result = result.replaceAll("零万零元", "万元");
		result = result.replaceAll("万零元", "万元");
		result = result.replaceAll("零亿", "亿");
		result = result.replaceAll("零万", "万");
		result = result.replaceAll("零元", "元");
		result = result.replaceAll("零零", "零");

		if ("元".equals(result.substring(0, 1)))
			result = result.substring(1);

		if ("零".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("角".equals(result.substring(0, 1)))
			result = result.substring(1);
		if ("分".equals(result.substring(0, 1)))
			result = result.substring(1);

		if ("整".equals(result.substring(0, 1)))
			result = "零元整";

		return result;
	}
	/**
	 * 字符串前补零操作。
	 * @param src
	 * @param length
	 * @return
	 */
	public static String patchZeroToString(String src, int length){
		StringBuffer result= new StringBuffer("");
		int index = src.trim().length();
		if(index<length){
			for(int i=(length-index);i>0;i--){
				result.append("0");	
			}
		}
		result.append(src);
		
		return result.toString();
	}
	/**
	 * 
	* @Title: patchEllipsisToString
	* @Description: TODO(截取指定长度并在后面补省略号)
	* @param src
	* @param length
	* @return
	 */
	public static String patchEllipsisToString(String src , int length){
		if(StringUtils.isBlank(src)) return "";
		if(src.length() <= length){
			return src;
		}else{
			return src.substring(0, length) + "...";
		}
	}
	
	
	/**
	 * 截取字段
	 * @param src
	 * @param length
	 * @return
	 */
	public static String substring(String src, int length){
		if(StringUtils.isEmpty(src)){
			return "";
		}
		
		src = StringUtils.trim(src);
		
		if(src.length()>length){
			src = src.substring(0, length)+"...";
		}
		return src;
	}
	
	public static String[] listToStringArray(List<Object> src){
		String[] result = new String[src.size()];
		for(int i=0;i<src.size();i++){
			result[i] = src.get(i).toString();
		}
		return result;
	}
	
	public static boolean isNumber(String src){
		if(StringUtils.isBlank(src)){
			return false;
		}
		Pattern p = Pattern.compile("^\\d+$");
		if(p.matcher(src).matches()){
			return true;
		}else{
			return false;
		}
	}
	 /**
     * 判断字符是否是中文
     *
     * @param c 字符
     * @return 是否是中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }
    
	/**
	 * 
	* @Title: isNumber
	* @Description: TODO(是否出现乱码)
	* @param src
	* @return
	 */
	public static boolean isMessyCode(String strName){
		 	Pattern p = Pattern.compile("\\s*|t*|r*|n*");
	        Matcher m = p.matcher(strName);
	        String after = m.replaceAll("");
	        String temp = after.replaceAll("\\p{P}", "");
	        char[] ch = temp.trim().toCharArray();
	        float chLength = ch.length;
	        float count = 0;
	        for (int i = 0; i < ch.length; i++) {
	            char c = ch[i];
	            if (!Character.isLetterOrDigit(c)) {
	                if (!isChinese(c)) {
	                    count = count + 1;
	                }
	            }
	        }
	        float result = count / chLength;
	        if (result > 0.4) {
	            return true;
	        } else {
	            return false;
	        }
	}
}
