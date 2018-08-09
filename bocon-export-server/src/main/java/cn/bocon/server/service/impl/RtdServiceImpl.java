package cn.bocon.server.service.impl;

import java.io.File;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;
import cn.bocon.server.common.StringUtils;
import cn.bocon.server.service.IResolveService;
import cn.bocon.server.service.IRtdService;

@Service
public class RtdServiceImpl implements IRtdService, IResolveService {
	private Logger logger = LoggerFactory.getLogger(RtdServiceImpl.class);

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

		List<String> polList = Lists.newArrayList();
		for (int i = 0; i < pols.length; i++) {

			String polCode = RegexUtils.getString(pols[i], "(\\w+)-Rtd");
			String rtd = RegexUtils.getString(pols[i], "-Rtd=((-)?\\d+(\\.\\d+)?)");
			String zsRtd = RegexUtils.getString(pols[i], "-ZsRtd=((-)?\\d+(\\.\\d+)?)");
			//String flag = RegexUtils.getString(pols[i], "-Flag=(\\w+)");
			map.put(polCode, rtd);
			polList.add(polCode);
			if (StringUtils.isNotEmpty(zsRtd)) {
				String zPolCode = polCode + "Z";
				polList.add(zPolCode);
				map.put(zPolCode, zsRtd);
			}
		}
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
		sb.append("\r\n");
		System.out.println(sb.toString());

		try {
			Map<String, String> polMap = Maps.newHashMap();
			File directory = new File("");//设定为当前文件夹 
			File f = new File(directory.getAbsolutePath() + "\\pollantinfo.txt"); //获取绝对路径 
			List<String> readLines = Files.readLines(f, Charset.forName("UTF-8"));
			for (int i = 0; i < readLines.size(); i++) {
				String temp = readLines.get(i);
				String[] arr = temp.split(",");
				polMap.put(arr[0], arr[1]);
			}
			File file = new File("d:\\rtd_data.csv");
			if (!file.exists()) {
				file.createNewFile();
				StringBuffer sbHead = new StringBuffer();
				sbHead.append("数据时间");
				sbHead.append(",");
				for (int i = 0; i < polList.size(); i++) {
					String polCode = polList.get(i);
					String polName = polMap.get(polCode);
					if (StringUtils.isEmpty(polName)) {
						sbHead.append(polName);
					} else {
						sbHead.append("");
					}
					
					if (i != polList.size() - 1) {
						sbHead.append(",");
					}
				}
				sbHead.append("\r\n");
				Files.append(mn + "\r\n", file, Charset.forName("gb2312"));
				Files.append(sbHead.toString(), file, Charset.forName("gb2312"));
			}
			Files.append(sb.toString(), file, Charset.forName("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
