package cn.bocon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

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
	
}
