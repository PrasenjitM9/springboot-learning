package cn.bocon.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 校验工具
 * @author Administrator
 *
 */
public class DataPackageUtils {
	public static final String PACKAGE_HEAD = "##"; //包头
	public static final String PACKAGE_TAIL = "\r\n"; //包尾
	/**
	 * 
	 * @param now
	 * @return true:校验通过 false:校验失败
	 */
	public static boolean checkDateTime(Date now) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		try {
			if (now == null) {
				return false;
			}
			String beforeStr = "2010-01-01 00:00:00";
			String afterStr = "";
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			int year = calendar.get(Calendar.YEAR);
			afterStr = year + "-12-31 23:59:59";
			
			calendar.setTime(sdf.parse(beforeStr));
			Date before = calendar.getTime(); //以前的日期
			
			calendar.setTime(sdf.parse(afterStr));		
			Date after = calendar.getTime(); //以后的日期
			
			//排除掉2010年度数据和大于当年的时间
			if (now.before(before) || now.after(after)) {
				return false;
			}			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * 
	 * 校验crc
	 * true:校验通过  false:校验失败
	 */
	public static boolean checkCrc(String msg) {
		boolean flag = false; //默认校验不通过
		if (StringUtils.isEmpty(msg)) {
			return flag;
		}
		String preCrc = msg.substring(msg.length() - 4);
		String crc = generateCrc(msg.substring(6, msg.length() - 4));
		if (crc.equalsIgnoreCase(preCrc)) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 生成crc
	 * @param msg
	 * @return
	 */
	public static String generateCrc(String msg) {
		Crc16 crc16 = new Crc16();
		int crc = crc16.tocrc16(msg.getBytes());
		String gethexstr = Integer.toHexString(crc);
		if (gethexstr.length() < 4) {
			gethexstr = "0" + gethexstr;
		}
		if (crc == 0) {
			gethexstr = "0000";
		}		
		return gethexstr.toUpperCase();
	}	
	
	/**
	 * 加上包头和数据段长度
	 * @param getostrh
	 * @return
	 */
	public static String getDataLength(String getostrh) {
		String dataLength = "";
		if ((getostrh.length()) >= 1000) {
			dataLength = String.valueOf(getostrh.length());
		} else if ((getostrh.length()) >= 100 && (getostrh.length()) < 1000) {
			dataLength = "0" + getostrh.length();
		} else if ((getostrh.length()) >= 10 && (getostrh.length()) < 100) {
			dataLength =  "00" + (getostrh.length());
		} else if ((getostrh.length()) < 10) {
			dataLength =  "000" + (getostrh.length());
		}
		return dataLength;
	}	
	
	/**
	 * 
	* @Title: composeDataPackage
	* @Description: 组装数据包
	* @param msg
	* @param 1:是反控包 0：不是反控包
	* @return
	 */
	public static String composeDataPackage(String msg, boolean flag) {
		String dataLength = getDataLength(msg);
		String crc = generateCrc(msg);
		StringBuffer sb = new StringBuffer();
		sb.append(PACKAGE_HEAD);
		sb.append(dataLength);
		sb.append(msg);
		sb.append(crc);
		if (flag == true) {
			sb.append("**"); //反控包
		}
		sb.append(PACKAGE_TAIL);
		return sb.toString();
	}
	
	/**
	 * 
	* @Title: generateQn
	* @Description: 生成qn号
	* @return
	 */
	public synchronized static String generateQn() {
		String qn = DateUtil.dateToString(new Date(), DateUtil.DATE_ALL);
		return qn;
	}
}