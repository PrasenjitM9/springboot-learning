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

import cn.bocon.server.common.Constants;
import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;
import cn.bocon.server.common.StringUtils;
import cn.bocon.server.service.IHisService;
import cn.bocon.server.service.IPollantinfoService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

@Service
public class HisServiceImpl implements IHisService {
	private Logger logger = LoggerFactory.getLogger(HisServiceImpl.class);
	@Autowired
	private IPollantinfoService pollantinfoService;
	public static List<String> polList = Lists.newArrayList();

	@Transactional
	public void resolve(String msg) {
		logger.info(msg);
		Map<String, Object> parsedMap = DataPackageUtils.parse(msg);
		String dataTime = (String) parsedMap.get("DataTime"); // 数据时间
		String mn = (String) parsedMap.get("MN"); // 设备号
		String cn = (String) parsedMap.get("CN");
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
			String polCode = RegexUtils.getString(pols[i], "(\\w+)-Avg");
			String avg = RegexUtils.getString(pols[i], "-Avg=((-)?\\d+(\\.\\d+)?)"); // 均值
			String min = RegexUtils.getString(pols[i], "-Min=((-)?\\d+(\\.\\d+)?)"); // 最小值
			String max = RegexUtils.getString(pols[i], "-Max=((-)?\\d+(\\.\\d+)?)"); // 最大值
			String cou = RegexUtils.getString(pols[i], "-Cou=((-)?\\d+(\\.\\d+)?)"); // 累计值

			String zsAvg = RegexUtils.getString(pols[i], "-ZsAvg=((-)?\\d+(\\.\\d+)?)"); // 折算均值
			String zsMin = RegexUtils.getString(pols[i], "-ZsMin=((-)?\\d+(\\.\\d+)?)"); // 折算最小值
			String zsMax = RegexUtils.getString(pols[i], "-ZsMax=((-)?\\d+(\\.\\d+)?)"); // 折算最大值
			//String flag = RegexUtils.getString(pols[i], "-Flag=(\\w+)");
			
			if (StringUtils.isNotEmpty(avg)) {
				if (!polList.contains(polCode)) {
					polList.add(polCode);
				}
				map.put(polCode + "-Avg", avg);
				map.put(polCode + "-Min", min);
				map.put(polCode + "-Max", max);	
				map.put(polCode + "-Cou", cou);	
			} 
			if (StringUtils.isNotEmpty(zsAvg)) {
				String zPolCode = polCode + "Z";
				if (!polList.contains(polCode)) {
					polList.add(zPolCode);
				}
				map.put(zPolCode + "-Avg", zsAvg);
				map.put(zPolCode + "-Min", zsMin);
				map.put(zPolCode + "-Max", zsMax);
			}
		}

		try {
			Map<String, String> polMap = pollantinfoService.getPollantinfo();
			File file = findFile(cn);

			addDataRow(file, now, polList, map);
			List<String> readLines = Files.readLines(file, Charset.forName("gb2312"));
			//把前面三行去掉
			if (readLines != null && readLines.size() > 3) {
				readLines = readLines.subList(3, readLines.size());
			}
			addFirst(file, mn);
			addSecondRow(file, polList, polMap);
			//增加最小值 平均值 最大值 累计值
			addThirdRow(file, polList);
			
			for (String line : readLines) {
				Files.append(line + Constants.TAIL, file, Charset.forName("UTF-8"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询文件
	private File findFile(String cn) throws Exception {
		File file = null;
		switch (cn) {
		case "2051":
			file = new File("d:\\minute_data.csv");
			break;
		case "2061":
			file = new File("d:\\hour_data.csv");
			break;
		case "2031":
			file = new File("d:\\day_data.csv");
			break;
		case "2081":
			file = new File("d:\\month_data.csv");
			break;				
		default:
			break;
		}

		if (!file.exists()) {
			file.createNewFile();
		}
		return file;
	}
	
	//第一行
	private void addFirst(File file, String mn) throws Exception {
		Files.write(mn + Constants.TAIL, file, Charset.forName("gb2312"));
	}
	
	//第二行
	private void addSecondRow(File file, List<String> polList, Map<String, String> polMap) throws Exception {
		StringBuffer sbHead = new StringBuffer();
		sbHead.append("数据时间");
		sbHead.append(",");
		for (int i = 0; i < polList.size(); i++) {
			String polCode = polList.get(i);
			String polName = polMap.get(polCode);
			if (StringUtils.isNotEmpty(polName)) {
				sbHead.append(polName);
			} else {
				sbHead.append("");
			}
			
			if (polCode.contains("Z")) {
				sbHead.append(",");
				sbHead.append(",");
			} else {
				sbHead.append(",");
				sbHead.append(",");
				sbHead.append(",");
			}

			if (i != polList.size() - 1) {
				sbHead.append(",");
			}
		}
		sbHead.append(Constants.TAIL);
		Files.append(sbHead.toString(), file, Charset.forName("gb2312"));
	}	

	//第三行 
	private void addThirdRow(File file, List<String> polList) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(",");
		for (int i = 0; i < polList.size(); i++) {
			String pol = polList.get(i);
			if (pol.contains("Z")) {
				sb.append("最小值");
				sb.append(",");
				sb.append("平均值");
				sb.append(",");
				sb.append("最大值");
			} else {
				sb.append("最小值");
				sb.append(",");
				sb.append("平均值");
				sb.append(",");
				sb.append("最大值");
				sb.append(",");
				sb.append("累计值");					
			}
			if (i != polList.size() - 1) {
				sb.append(",");
			}			
		}
		Files.append(sb.toString() + Constants.TAIL, file, Charset.forName("gb2312"));
	}
	
	/**
	 * 数据行
	 * @param file
	 * @param now
	 * @param polList
	 * @param map
	 * @throws Exception
	 */
	private void addDataRow(File file, Date now, List<String> polList, Map<String, Object> map) throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(DateUtils.dateToString(now, DateUtils.DATETIME_FORMAT));
		sb.append(",");
		for (int i = 0; i < polList.size(); i++) {
			String polCode = polList.get(i);
			String avg = (String) map.get(polCode + "-Avg");
			String min = (String) map.get(polCode + "-Min");
			String max = (String) map.get(polCode + "-Max");
			String cou = (String) map.get(polCode + "-Cou");
			
			sb.append(StringUtils.replaceEmpty(min));
			sb.append(",");
			sb.append(StringUtils.replaceEmpty(avg));
			sb.append(",");
			sb.append(StringUtils.replaceEmpty(max));
			if (StringUtils.isNotEmpty(cou)) {
				sb.append(",");
				sb.append(StringUtils.replaceEmpty(cou));
			}
			
			if (i != polList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append(Constants.TAIL);
		Files.append(sb.toString(), file, Charset.forName("UTF-8"));
	}
}
