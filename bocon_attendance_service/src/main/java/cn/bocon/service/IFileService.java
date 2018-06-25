package cn.bocon.service;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IFileService {

	/**
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void handleData(File file) throws Exception;
	
	/**
	 * 导出
	 */
	public List<Map<String, Object>> export() throws Exception;
}
