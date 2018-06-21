package cn.bocon.server.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.bocon.server.service.impl.ExcelServiceImpl;

import com.google.common.collect.Maps;

public class ProcessUtils {
	private static Logger logger = LoggerFactory.getLogger(ProcessUtils.class);
	public static Map<String, Object> map;
	
	static {
		map = Maps.newHashMap();
		map.put("flag", false); //默认没有进程
		map.put("second", 0); //还有多少秒关闭
	}

	public static boolean isRunning(String processName)//判断有没有在运行考试软件
    {
       
        BufferedReader bufferedReader = null;
        try
        {
            Process proc = Runtime.getRuntime().exec("tasklist /FI \"IMAGENAME eq "
                    + processName
                    + "\"");
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                if (line.contains(processName)) //判断是否存在
                {
                    return true;
                }
            }
            return false;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return false;
        }
        finally
        {
            if (bufferedReader != null)
            {
                try
                {
                    bufferedReader.close();
                }
                catch (Exception ex)
                {
                }
            }
        } 
    }
	
	/**
	 * @param args
	 */
	public static void run() throws Exception {
/*		boolean isrun=isRunning("cmd.exe");
		if(isrun){
			System.out.println("有在运行cmd.exe");
		}else{
			System.out.println("没有运行cmd.exe");
		}*/
		boolean isrun=isRunning("EXCEL.EXE");
		if(isrun){
			logger.info("有在运行EXCEL.exe");
			boolean flag = (Boolean)map.get("flag");
			if (flag == false) {
				map.put("flag", true);
				for (int i = 0; i < 30; i++) {
					TimeUnit.SECONDS.sleep(1);
					map.put("second", 30 - i - 1);
					logger.info(String.valueOf(30 - i - 1));
				}
				
				Runtime.getRuntime().exec("Taskkill /f /IM EXCEL.EXE");		
				map.put("flag", false);
			}

		}else{
			logger.info("没有运行EXCEL.exe");
		}		
	}
}