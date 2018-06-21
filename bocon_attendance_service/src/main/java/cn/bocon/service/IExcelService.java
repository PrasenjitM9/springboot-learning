package cn.bocon.service;

import java.util.Map;

public interface IExcelService {

	/**
	 * 
	 * @param templateFileName 模板名称
	 * @param beans
	 * @param destFileName
	 * @throws Exception
	 */
	public void export(String templateFileName, Map<String, Object> beans,
			String destFileName) throws Exception;

}