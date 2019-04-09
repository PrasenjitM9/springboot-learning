package cn.bocon.server.web;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import cn.bocon.server.common.Constants;
import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;
import cn.bocon.server.service.IResolveService;
import cn.bocon.server.service.IRtdService;

@RestController
@RequestMapping("/dataCenter")
public class DataCenterController {
	private Logger logger = LoggerFactory.getLogger(DataCenterController.class);
	@Autowired
	private IRtdService rtdService;	
	@Value("${bocon.checkCrc}")
	private String checkCrc;
	@Value("${bocon.checkValidDate}")
	private String checkValidDate;

	// 解析实时数据
	@RequestMapping(value = "resolve", method = RequestMethod.POST)
	public String resolve(String msg, Model model) {
		String timeStr = DateUtils.getCurrentTimeString();
		Map<String, String> map = Maps.newHashMap();
		map.put("time", timeStr);
		map.put("msg", msg);
		Constants.dataPackages.add(map);
		if (Constants.dataPackages.size() > 100) {
			Constants.dataPackages.remove(0);
		}
		if ("1".equals(checkValidDate)) { //校验有效期
			Date now = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.set(2018, 4, 10);
			if (now.after(calendar.getTime())) {
				return "";
			}
		}
		Preconditions.checkNotNull(msg, "数据报文应不为空");
		IResolveService resolveService = null;
		//校验crc
		if ("1".equals(checkCrc)) {
			//校验crc
			boolean checkCrc = DataPackageUtils.checkCrc(msg);
			if (checkCrc ==  false) {
				logger.error("crc校验失败：" + msg);
				return "crc校验失败" + msg;
			}			
		}		
		
		logger.info(msg);
		//校验时间
		Date dateTimeDate = null;
		String dataTime = RegexUtils.getString(msg, "DataTime=(\\w+);"); // 数据时间
		if (StringUtils.isNotEmpty(dataTime)) {
			dateTimeDate = DateUtils.stringToDate(dataTime, DateUtils.DATA_PACKAGE_FORMAT);
		}
		boolean checkDateTime = DataPackageUtils.checkDateTime(dateTimeDate);
		if (checkDateTime ==  false) {
			return null;
		}
		String cn = RegexUtils.getString(msg, "CN=(\\w+);");
		if ("2011".equals(cn)) { //实时数据
			resolveService = rtdService;
		}
		if (resolveService != null) {
			resolveService.resolve(msg);
		}
		return "ok";
	}
}
