package cn.bocon.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Maps;

import cn.bocon.common.DownloadUtils;
import cn.bocon.service.IAttendanceService;
import cn.bocon.service.IExcelService;
import jxl.common.Logger;

@Controller
public class IndexController {
	private Logger logger = Logger.getLogger(IndexController.class);
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
	public String exportBocon(HttpServletResponse response) {
		try {
			List<Map<String, Object>> datas = attendanceService.dealData();
			Map<String, Object> beans = Maps.newHashMap();
			beans.put("records", datas);
			
			String filePath = "D:\\博控打卡记录（处理后）.xls";
			filePath = excelService.export("考勤记录表.xls", beans, filePath);	
			DownloadUtils.writeFile(response, filePath, true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("导出博控考勤记录异常" + e.getMessage());
		}
		return null;
	}
	
}
