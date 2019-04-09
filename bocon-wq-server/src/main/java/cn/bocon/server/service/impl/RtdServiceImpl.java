package cn.bocon.server.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bocon.server.common.DataPackageUtils;
import cn.bocon.server.common.DateUtils;
import cn.bocon.server.common.RegexUtils;
import cn.bocon.server.service.IResolveService;
import cn.bocon.server.service.IRtdService;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

@Service
public class RtdServiceImpl implements IRtdService, IResolveService {
	private Logger logger = LoggerFactory.getLogger(RtdServiceImpl.class);
	
	@Transactional
	public void resolve(String msg) {
		Map<String, Object> parsedMap = DataPackageUtils.parse(msg);
		String dataTime = (String)parsedMap.get("DataTime"); //数据时间
		String mn = (String)parsedMap.get("MN");; //设备号
/*	    Date now = DateUtils.stringToDate(dataTime, DateUtils.DATA_PACKAGE_FORMAT);
	    //检查时间
	    if (!DataPackageUtils.checkDateTime(now)) {
	    	return;
	    }
		String pols[] = RegexUtils.getString(msg, "&&DataTime=\\d*;(.*)&&").split(";");

	    dataTime = DateUtils.dateToString(now, DateUtils.DATETIME_FORMAT); //DataTime字段值	\
	    Map<String, Object> map = Maps.newHashMap();
	    map.put("MN", mn);
	    map.put("DataTime", now);
	    
		for (int i = 0; i < pols.length; i++) {
			
			String polCode = RegexUtils.getString(pols[i], "(\\w+)-Rtd");
			String rtd = RegexUtils.getString(pols[i], "-Rtd=(\\d+(\\.\\d+)?)");
			//String zsRtd = RegexUtils.getString(pols[i], "-ZsRtd=(\\d+(\\.\\d+)?)");
			//String flag = RegexUtils.getString(pols[i], "-Flag=(\\w+)");
			map.put(polCode, rtd);
		}*/
		
	}	
	
	
}
