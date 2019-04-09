package cn.bocon.server.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * 校验工具
 * @author wangjian
 *
 */
public class DataPackageUtils {
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
	public static boolean checkK29Crc(String msg) {
		boolean flag = false; //默认校验不通过
		if (StringUtils.isEmpty(msg)) {
			return flag;
		}
		String preCrc = msg.substring(msg.length() - 4);
		String crc = generateCrc(msg.substring(0, msg.length() - 4));
		if (crc.equalsIgnoreCase(preCrc)) {
			flag = true;
		}
		return flag;
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
		String qn = DateUtils.dateToString(new Date(), DateUtils.DATE_ALL);
		return qn;
	}
	
	/**
	 * 
	* @Title: parse
	* @Description: 将数据报文转成map 有多个值的用|分隔
	* @return
	 */
	public static Map<String, Object> parse(String msg) {
		Preconditions.checkNotNull(msg, "数据包应不为空！");
		Map<String, Object> dataSegmentMap = Maps.newHashMap(); //数据段数据map
		//处理非数据段
		String other = RegexUtils.getString(msg, "##\\d{4}(.+);CP=&&.*&&");
		String[] otherArray = other.split(";");
		for (String temp : otherArray) {
			String[] array = temp.split("=");
			String key = array[0];
			String value = "";
			if (array.length == 2) {
				value = array[1];	
			}
			dataSegmentMap.put(key, value);
		}

		//处理时间
		String dataTime = RegexUtils.getString(msg, "&&DataTime=(\\d*);.*&&");
		dataSegmentMap.put("DataTime", dataTime);
		
		List<Map<String, String>> datas = Lists.newArrayList();
		//处理数据段
		String dataSegment = RegexUtils.getString(msg, "&&DataTime=\\d*;(.+)&&");
		//根据竖线分割
		String[] array1 = dataSegment.split("\\|");
		for (String temp1 : array1) {
			String[] array2 = temp1.split(";");
			for (String temp2 : array2) {
				String[] array3 = temp2.split("=");
				String key = array3[0];
				String value = null;
				if (array3.length == 2) {
					value = array3[1];
					if (dataSegmentMap.containsKey(key)) {
						String preValue = (String)dataSegmentMap.get(key);
						dataSegmentMap.put(key, preValue + "|" + value);
					} else {
						dataSegmentMap.put(key, value);
					}					
				} else {
					datas.add(getDataMap(temp2));
				}
			}			
		}
		dataSegmentMap.put("datas", datas);
		
		//处理crc
		String crc = RegexUtils.getString(msg, "&&.*&&(.{4})"); 
		dataSegmentMap.put("CRC", crc);
		return dataSegmentMap;
	}	
	
	/**
	 * 
	* @Title: getDataMap
	* @Description: 获得数据map
	* @param msg 已经通过;分割的串
	* @return
	 */
	public static Map<String, String> getDataMap(String msg) {
		Map<String, String> resultmap = Maps.newHashMap();
		String polCode = RegexUtils.getString(msg, "(\\w+)-Rtd");
		String rtd = RegexUtils.getString(msg, "-Rtd=(\\d+(\\.\\d+)?)");
		String zsRtd = RegexUtils.getString(msg, "-ZsRtd=(\\d+(\\.\\d+)?)");
		String flag = RegexUtils.getString(msg, "-Flag=(\\w+)");	
		String min = RegexUtils.getString(msg, "-Min=(\\d+(\\.\\d+)?)");	
		String max = RegexUtils.getString(msg, "-Max=(\\d+(\\.\\d+)?)");	
		String avg = RegexUtils.getString(msg, "-Avg=(\\d+(\\.\\d+)?)");	
		String cou = RegexUtils.getString(msg, "-Cou=(\\d+(\\.\\d+)?)");	
		
		if (StringUtils.isEmpty(polCode)) {
			polCode = RegexUtils.getString(msg, "(\\w+)-Avg");
		}
		if (StringUtils.isNotEmpty(polCode)) {
			resultmap.put("polCode", polCode);
		} 
		if (StringUtils.isNotEmpty(rtd)) {
			resultmap.put("rtd", rtd);
		}
		if (StringUtils.isNotEmpty(zsRtd)) {
			resultmap.put("zsRtd", zsRtd);
		}
		if (StringUtils.isNotEmpty(flag)) {
			resultmap.put("flag", flag);
		}
		if (StringUtils.isNotEmpty(min)) {
			resultmap.put("min", min);
		}	
		if (StringUtils.isNotEmpty(max)) {
			resultmap.put("max", max);
		}	
		if (StringUtils.isNotEmpty(avg)) {
			resultmap.put("avg", avg);
		}						
		if (StringUtils.isNotEmpty(cou)) {
			resultmap.put("cou", cou);
		}		
		return resultmap;
	}
	
	public static void main(String[] args) {
		//String msg = "##0218ST=32;CN=2011;PW=123456;,MN=88888881234567;FLAG=0;CP=&&DataTime=20180306120000;001-Rtd=6.951,001-Flag=N;060-Rtd=23.400,060-Flag=N;011-Rtd=134.119,011-Flag=N;B01-Rtd=382.222,B01-Flag=N;B00-Rtd=156720160.000,B00-Flag=N&&2940";
		//String msg = "##0624QN=20180303110906001;ST=32;CN=2011;PW=123456;MN=8888888000114W;Flag=4;CP=&&DataTime=20180303105900;a00000-Rtd=0.0000,a00000-Flag=D;a34013-Rtd=0.0000,a34013-ZsRtd=0.00,a34013-Flag=D;a21026-Rtd=0.0000,a21026-ZsRtd=0.00,a21026-Flag=D;a21002-Rtd=0.0000,a21002-ZsRtd=0.00,a21002-Flag=D;a21005-Rtd=0.0000,a21005-ZsRtd=0.00,a21005-Flag=D;a21018-Rtd=0.0000,a21018-ZsRtd=0.00,a21018-Flag=D;a21024-Rtd=0.0000,a21024-ZsRtd=0.00,a21024-Flag=D;a19001-Rtd=0.0000,a19001-Flag=D;a01011-Rtd=0.0000,a01011-Flag=D;a01012-Rtd=0.0000,a01012-Flag=D;a01017-Rtd=0.0000,a01017-Flag=D;a01014-Rtd=0.0000,a01014-Flag=D;a01013-Rtd=0.0000,a01013-Flag=D&&2A80";
		//String msg = "##0979QN=20180303104513001;ST=32;CN=2051;PW=123456;MN=88888880000137;Flag=6;PNUM=2;PNO=2;CP=&&DataTime=20180303101200;a21018-Cou=0.00,a21018-Min=0.00,a21018-Avg=0.00,a21018-Max=0.00,a21018-ZsMin=0.00,a21018-ZsAvg=0.00,a21018-ZsMax=0.00,a21018-Flag=D;a21024-Cou=0.00,a21024-Min=0.00,a21024-Avg=0.00,a21024-Max=0.00,a21024-ZsMin=0.00,a21024-ZsAvg=0.00,a21024-ZsMax=0.00,a21024-Flag=D;a19001-Cou=0.0000,a19001-Min=0.0000,a19001-Avg=0.0000,a19001-Max=0.0000,a19001-Flag=D;a01011-Cou=0.0000,a01011-Min=0.0000,a01011-Avg=0.0000,a01011-Max=0.0000,a01011-Flag=D;a01012-Cou=0.0000,a01012-Min=0.0000,a01012-Avg=0.0000,a01012-Max=0.0000,a01012-Flag=D;a01017-Cou=0.0000,a01017-Min=0.0000,a01017-Avg=0.0000,a01017-Max=0.0000,a01017-Flag=D;a01014-Cou=0.0000,a01014-Min=0.0000,a01014-Avg=0.0000,a01014-Max=0.0000,a01014-Flag=D;a01013-Cou=0.0000,a01013-Min=0.0000,a01013-Avg=0.0000,a01013-Max=0.0000,a01013-Flag=D;w01018-Cou=0.0000,w01018-Min=0.0000,w01018-Avg=0.0000,w01018-Max=0.0000,w01018-Flag=D&&E981";
		String msg = "##1045ST=31;CN=2051;PW=123456;MN=756877X7916010;Flag=2;PNUM=2;PNO=1;CP=&&DataTime=20180303102200;B02-Min=0.0000,B02-Avg=0.0000,B02-Max=0.0000,B02-Cou=0.0000,B02-Flag=D;30-Min=109.8557,30-Avg=109.8712,30-Max=109.8885,30-Cou=0.0000,30-Flag=N;31-Min=103.4228,31-Avg=103.4489,31-Max=103.4723,31-Cou=0.0000,31-Flag=N;32-Min=102.8947,32-Avg=102.9123,32-Max=102.9281,32-Cou=0.0000,32-Flag=N;33-Min=1003.0443,33-Avg=1003.0705,33-Max=1003.0940,33-Cou=0.0000,33-Flag=T;34-Min=1003.1765,34-Avg=1003.1824,34-Max=1003.2093,34-Cou=0.0000,34-Flag=T;35-Min=102.8606,35-Avg=102.8729,35-Max=102.8770,35-Cou=0.0000,35-Flag=N;36-Min=102.9935,36-Avg=103.0101,36-Max=103.0268,36-Cou=0.0000,36-Flag=N;37-Min=103.2597,37-Avg=103.2753,37-Max=103.2928,37-Cou=0.0000,37-Flag=N;01-Min=0.00,01-Avg=0.00,01-Max=0.00,01-Cou=0.00,01-ZsMin=0.00,01-ZsAvg=0.00,01-ZsMax=0.00,01-Flag=D;02-Min=0.00,02-Avg=0.00,02-Max=0.00,02-Cou=0.00,02-ZsMin=0.00,02-ZsAvg=0.00,02-ZsMax=0.00,02-Flag=D;03-Min=0.00,03-Avg=0.00,03-Max=0.00,03-Cou=0.00,03-ZsMin=0.00,03-ZsAvg=0.00,03-ZsMax=0.00,03-Flag=D&&AB41";
		Map<String, Object> map = parse(msg);
		System.out.println(map);
	}	
}