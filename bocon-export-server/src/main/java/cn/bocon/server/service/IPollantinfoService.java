package cn.bocon.server.service;

import java.util.Map;

/**
 * 污染物信息
 * @author wangjian
 *
 */
public interface IPollantinfoService {

	/**
	 * 获得污染物编码
	 * @return
	 */
	Map<String, String> getPollantinfo() throws Exception;
}
