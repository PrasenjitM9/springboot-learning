package cn.bocon.server.web;

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
import cn.bocon.server.service.IHisService;
import cn.bocon.server.service.IResolveService;
import cn.bocon.server.service.IRtdService;

/**
 * 数据中心控制器
 * @author wangjian
 *
 */
@RestController
@RequestMapping("/dataCenter")
public class DataCenterController {
	private Logger logger = LoggerFactory.getLogger(DataCenterController.class);
	@Value("${bocon.checkCrc}")
	private String checkCrc;
	
	@Autowired
	private IRtdService rtdService;	
	@Autowired
	private IHisService hisService;

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
		switch (cn) {
			case "2011":
				resolveService = rtdService; //实时数据
				break;
			case "2051":
				resolveService = hisService; //分钟数据
				break;
			case "2061":
				resolveService = hisService; //小时数据
				break;
			case "2031":
				resolveService = hisService; //日数据
				break;				
			case "2081":
				resolveService = hisService; //月数据
				break;	
			default:
				break;
		}
		if (resolveService != null) {
			resolveService.resolve(msg);
		}
		return "ok";
	}
}
