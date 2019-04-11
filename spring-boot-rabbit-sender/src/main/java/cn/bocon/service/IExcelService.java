package cn.bocon.service;

import java.util.Map;

public interface IExcelService {

	/**
	 * 
	 * @param templateFileName 模板名称
	 * @param beans
	 * @param destFileName
	 * @return 文件路径
	 * @throws Exception
	 */
	public String export(String templateFileName, Map<String, Object> beans,
			String destFileName) throws Exception;

}