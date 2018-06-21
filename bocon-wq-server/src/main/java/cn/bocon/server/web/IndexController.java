package cn.bocon.server.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.bocon.server.common.Constants;


@Controller
public class IndexController {
	/**
	 * 跳转首页
	 * 
	 * @return
	 */
	@RequestMapping(value = { "", "/", "index" }, method = RequestMethod.GET)
	public String index(ModelMap map) {
		List<Map<String, String>> dataPackages = Constants.dataPackages;
		map.addAttribute("dataPackages", dataPackages);
		return "dataList";
	}
	
	/**
	 * 跳转首页
	 * 
	 * @return
	 */
	@RequestMapping(value = {"/onlines"}, method = RequestMethod.GET)
	public String onlines(ModelMap map) {
		Map<String, String> onlines = Constants.onlines;
		map.addAttribute("onlines", onlines);
		return "pointList";
	}	

	/**
	 * 跳转文件上传页面
	 * 
	 * @return
	 */
	@RequestMapping(value = { "myfile"}, method = RequestMethod.GET)
	public String myfile(ModelMap map) {
		return "fileForm";
	}
}