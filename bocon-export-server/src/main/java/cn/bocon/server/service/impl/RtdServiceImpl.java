package cn.bocon.server.service.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import cn.bocon.server.common.Constants;
import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;
import cn.bocon.server.common.StringUtils;
import cn.bocon.server.service.IPollantinfoService;
import cn.bocon.server.service.IRtdService;

@Service
public class RtdServiceImpl implements IRtdService {
	private Logger logger = LoggerFactory.getLogger(RtdServiceImpl.class);
	@Autowired
	private IPollantinfoService pollantinfoService;
	public static List<String> polList = Lists.newArrayList();

	@Transactional
	public void resolve(String msg) {
		logger.info(msg);
		Map<String, Object> parsedMap = DataPackageUtils.parse(msg);
		String dataTime = (String) parsedMap.get("DataTime"); // 数据时间
		String mn = (String) parsedMap.get("MN"); // 设备号
		Date now = DateUtils.stringToDate(dataTime, DateUtils.DATA_PACKAGE_FORMAT);
		// 检查时间
		if (!DataPackageUtils.checkDateTime(now)) {
			return;
		}
		String pols[] = RegexUtils.getString(msg, "&&DataTime=\\d*;(.*)&&").split(";");

		dataTime = DateUtils.dateToString(now, DateUtils.DATETIME_FORMAT); // DataTime字段值
		Map<String, Object> map = Maps.newHashMap();
		map.put("MN", mn);
		map.put("DataTime", now);

		for (int i = 0; i < pols.length; i++) {

			String polCode = RegexUtils.getString(pols[i], "(\\w+)-Rtd");
			String rtd = RegexUtils.getString(pols[i], "-Rtd=((-)?\\d+(\\.\\d+)?)");
			String zsRtd = RegexUtils.getString(pols[i], "-ZsRtd=((-)?\\d+(\\.\\d+)?)");
			//String flag = RegexUtils.getString(pols[i], "-Flag=(\\w+)");
			map.put(polCode, rtd);
			if (!polList.contains(polCode)) {
				polList.add(polCode);
			}
			
			if (StringUtils.isNotEmpty(zsRtd)) {
				String zPolCode = polCode + "Z";
				if (!polList.contains(polCode)) {
					polList.add(zPolCode);
				}
				map.put(zPolCode, zsRtd);
			}
		}

		try {
			Map<String, String> polMap = pollantinfoService.getPollantinfo();
			File file = new File("d:\\rtd_data.csv");
			
			if (!file.exists()) {
				file.createNewFile();
			}
			//添加数据行
			addDataRow(file, now, polList, map);
			List<String> readLines = Files.readLines(file, Charset.forName("gb2312"));
			//把前面两行去掉
			if (readLines != null && readLines.size() > 2) {
				readLines = readLines.subList(2, readLines.size());
			}
			addFirstRow(file, mn);
			addSecondRow(file, polList, polMap);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 第一行
	 * @param file 
	 * @param mn 设备唯一标识
	 * @throws Exception
	 */
	private void addFirstRow(File file, String mn) throws Exception {
		Files.append(mn + Constants.TAIL, file, Charset.forName("gb2312"));
	}

	/**
	 * 第二行
	 * @param file
	 * @param polList 污染物列表
	 * @param polMap 污染物字典
	 * @throws Exception
	 */
	private void addSecondRow(File file, List<String> polList, Map<String, String> polMap) throws Exception {
		StringBuffer sbHead = new StringBuffer();
		sbHead.append("数据时间");
		sbHead.append(",");
		for (int i = 0; i < polList.size(); i++) {
			String polCode = polList.get(i);
			String polName = polMap.get(polCode);
			if (StringUtils.isEmpty(polName)) {
				polName = polCode;
			}
			sbHead.append(polName);
			
			if (i != polList.size() - 1) {
				sbHead.append(",");
			}
		}
		sbHead.append(Constants.TAIL);
		Files.append(sbHead.toString(), file, Charset.forName("gb2312"));
	}	
	
	/**
	 * 增加数据行
	 */
	private void addDataRow(File file, Date now, List<String> polList, Map<String, Object> map) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtils.dateToString(now, DateUtils.DATETIME_FORMAT));
		sb.append(",");
		for (int i = 0; i < polList.size(); i++) {
			String polCode = polList.get(i);
			String value = (String) map.get(polCode);
			sb.append(value);
			if (i != polList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(Constants.TAIL);		
		Files.append(sb.toString(), file, Charset.forName("UTF-8"));
	}
}
