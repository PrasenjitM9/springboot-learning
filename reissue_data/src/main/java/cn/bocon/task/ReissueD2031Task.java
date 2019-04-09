package cn.bocon.task;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.bocon.common.DataPackageUtils;
import cn.bocon.common.DateUtil;
import cn.bocon.entity.Controlpoint;
import cn.bocon.entity.Returnrevs;
import cn.bocon.service.IControlpointService;
import cn.bocon.service.IHisService;
import cn.bocon.service.IReturnrevsService;
import cn.bocon.service.ITelnetClientService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;

@Component
public class ReissueD2031Task {
	@Autowired
	private IControlpointService controlpointService;
	@Autowired
	private IHisService hisService;
	@Autowired
	private IReturnrevsService returnrevsService;
	@Autowired
	private ITelnetClientService telnetClientService;

    @Scheduled(cron = "0 0 9 * * ?")
	public void reissue() {
    	// 创建一个最多同时运行 3 个任务的线程池  
    	ExecutorService service = Executors.newFixedThreadPool(3);  
    	//查询监控点信息
    	List<Controlpoint> controlpoints = controlpointService.selectList(null);
    	
    	if (controlpoints == null || controlpoints.size() == 0) {
    		return;
    	}
    	
    	for (int i = 0; i < controlpoints.size(); i++) {
    		final Controlpoint controlpoint = controlpoints.get(i);
    		final int taskID = i;  
    		//根据数据时间查询日数据
    		service.execute(new Runnable() {
				
				@Override
				public void run() {
			    	Date now = new Date(); //当前日期
			    	Calendar nowCalendar = Calendar.getInstance();
			    	nowCalendar.setTime(now);
			    	nowCalendar.add(Calendar.DAY_OF_MONTH, -1);
			        String startTime = "2017-08-01 00:00:00";
			        Date date = DateUtil.stringToDate(startTime, DateUtil.DATETIME_FORMAT);
			        Calendar calendar = Calendar.getInstance();
			        calendar.setTime(date);						
			        //一致补到昨天
			        while (calendar.getTime().before(nowCalendar.getTime())) {
			        	String datatime = DateUtil.formatDateTime(calendar.getTime());
				    	final String dateStr = DateUtil.dateToString(calendar.getTime(), DateUtil.DATA_PACKAGE_FORMAT);					
				   		Map<String, Object> map = Maps.newHashMap();
			    		map.put("mnnum", controlpoint.getMnnum());
			    		map.put("datatime", datatime);
			    		map.put("cn", "2031");
			    		int count = hisService.findCount(map);
			    		if (count == 0) {
			    			//组装数据包，补发
			    			String msg = "";
			    			String qn = DataPackageUtils.generateQn();
			    			String template = "QN={qn};ST=32;CN=2031;PW=123456;MN={mn};Flag=3;CP=&&BeginTime={beginTime},EndTime={endTime}&&"; 
			    			StringBuffer sb = new StringBuffer();
			    			sb.append("QN=").append(qn);
			    			template = template.replace("{qn}", qn);
			    			template = template.replace("{mn}", controlpoint.getMnnum());
			    			template = template.replace("{beginTime}", dateStr);
			    			template = template.replace("{endTime}", dateStr);
			    			msg = DataPackageUtils.composeDataPackage(template, true);
			    			System.out.println("第" + (taskID + 1) + "("+ controlpoint.getMnnum() +")个任务 执行" + datatime + "日数据补发" + msg);
			    			//查询反控结果 收到9012包发送下一个命令
			    			try {
			    				telnetClientService.sendMsg(msg);
							} catch (Exception e) {
								e.printStackTrace();
							}
			    			for (int j = 0; j < 3; j++) {
			    				try {
			    					Thread.sleep(3000);
			    				} catch (InterruptedException e) {
			    					e.printStackTrace();
			    				}
			    				Wrapper<Returnrevs> resultWrapper = new EntityWrapper<>();
			    				resultWrapper.eq("qn", qn);
			    				resultWrapper.eq("cn", "9012");
			    				Returnrevs result = returnrevsService.selectOne(resultWrapper);
			    				if (result != null) { //有收到9012包
			    					break;
			    				}
			    			}    			
			    		}			      
			    		  
			        	//+1天
			        	calendar.add(Calendar.DAY_OF_MONTH, 1);
			        }  					
				}
			});
    	}
    	service.shutdown();// 任务执行完毕，关闭线程池  
    }
}
