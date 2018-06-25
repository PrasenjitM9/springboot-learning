package cn.bocon.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

import cn.bocon.service.IAttendanceService;
import cn.bocon.service.IExcelService;

@Controller
public class IndexController {

	@Autowired
	private IExcelService excelService;
	@Autowired
	private IAttendanceService attendanceService;	
	
	/**
	 * 跳转首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/", "index" }, method = RequestMethod.GET)
	public String index(ModelMap map) {
		return "index";
	}
	
	/**
	 * 处理excel
	 * 
	 * @return
	 */
	@RequestMapping(value = { "handle"})
	public String handle(ModelMap map) {
		return "index";
	}

	/**
	 * 处理excel
	 * 
	 * @return
	 */
	@RequestMapping(value = { "exportBocon"})
	public String exportBocon() {
		try {
			List<Map<String, Object>> datas = attendanceService.dealData();
			Map<String, Object> beans = Maps.newHashMap();
			beans.put("records", datas);
			
			String fileName = "D:\\2018年5月份博控打卡记录（处理后）.xls";
			excelService.export("考勤记录表.xls", beans, fileName);				
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
