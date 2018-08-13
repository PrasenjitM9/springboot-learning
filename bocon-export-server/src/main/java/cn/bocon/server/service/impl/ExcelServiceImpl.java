package cn.bocon.server.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cn.bocon.server.common.ProcessUtils;
import cn.bocon.server.service.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService  {
	private Logger logger = LoggerFactory.getLogger(ExcelServiceImpl.class);
	
	@Override
	//@Scheduled(cron = "0/2 * * * * ?")
	public void close() {
		try {
			try {
				ProcessUtils.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}		
	}
	
	//@Scheduled(cron = "30 0/1 * * * ?")
	public void open() {
		String addr="D:\\TmpData.xls";
		try {
			//Runtime.getRuntime().exec("C://Program Files//Microsoft Office//OFFICE11//EXCEL.EXE " +addr);
			Runtime.getRuntime().exec("C://Program Files (x86)//Microsoft Office//OFFICE11//EXCEL.EXE " +addr);			
			//Runtime.getRuntime().exec("EXCEL.EXE " +addr);
		} catch (Exception e) {
			logger.error("打开excel异常" + e.getMessage());
			e.printStackTrace();
		}		
	}
	
	//@Scheduled(cron = "50 0/1 * * * ?")
	public void autoclose() {   
		try {
			Runtime.getRuntime().exec("Taskkill /f /IM EXCEL.EXE");	
		} catch (Exception e) {
			logger.error("自动excel异常" + e.getMessage());
			e.printStackTrace();
		}		
	}	
}
