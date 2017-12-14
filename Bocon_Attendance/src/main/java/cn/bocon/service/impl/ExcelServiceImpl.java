package cn.bocon.service.impl;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.bocon.service.IExcelService;

import net.sf.jxls.transformer.XLSTransformer;

@Service
public class ExcelServiceImpl implements IExcelService {

	
	
    /**
     * 
     * @param templateFileName 模板名称
     * @param beans
     * @param destFileName
     * @throws Exception
     */
    public void export(String templateFileName, Map<String, Object> beans, String destFileName) throws Exception {
    	try {
	  		  XLSTransformer transformer = new XLSTransformer();
	  		  //拼接路径
	  		  String absolutePath = File.separator + "templates" + File.separator + templateFileName;
	  		  String fileName = (ExcelServiceImpl.class.getClassLoader().getResource("").getPath() + absolutePath).substring(1).replace("%20"," ");
	  		  transformer.transformXLS(fileName, beans, destFileName);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }
    
    
}
