package cn.bocon.service.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bocon.common.DateUtil;
import cn.bocon.common.StringUtils;
import cn.bocon.service.IAttendanceService;
import cn.bocon.service.ISysAllHolidayService;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

@Service
public class AttendanceServiceImpl implements IAttendanceService {
	@Autowired
	private ISysAllHolidayService sysAllHolidayService;
	
	public List<Map<String, Object>> dealData() throws Exception {
		String workStartTime = " 08:45:00";
		String workEndTime = " 17:45:00";
		int year = 0;
		int month = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, 5);
		//System.out.println(calendar.get(Calendar.MONTH));
		
		String testFilePath = "d:\\test.txt";
		File testFile = new File(testFilePath);
		List<String> lines = Files.readLines(testFile, Charsets.UTF_8);
		List<Map<String, Object>> lists = Lists.newArrayList();
		Set<Map<String, String>> noSet = new HashSet<>();
		for (String s : lines) {
			//去除两端的空格，并把中间的连续空格替换成单个空格
			if (StringUtils.isEmpty(s)) {
				continue;
			}
			String spaced = CharMatcher.WHITESPACE.trimAndCollapseFrom(s, ' ');
			String arr[] = spaced.split(" ");
			//多于两次的打卡
			String startTime = null;
			startTime = arr[4]; //开始时间
			String endTime  = null;
			if (arr.length == 5) { //只有一次打卡
				int hour = Integer.valueOf(startTime.split(":")[0]);
				if (hour > 12) {
					endTime = startTime;
					startTime = null;
				} else {
					endTime = null;
				}
			}
			
			if (arr.length - 1 > 4) {
				endTime = arr[arr.length - 1]; //结束时间
			}

			String dept = arr[0]; //部门
			String no = arr[1]; //员工编号
			
			String name = arr[2]; //姓名
			String date = arr[3]; //日期
			if (year == 0) {
				year = Integer.valueOf(arr[3].split("-")[0]); //年度
			}
			if (month == 0) {
				month = Integer.valueOf(arr[3].split("-")[1]); //月度
			}
			//如果两次打卡时间相差五分钟以内
			if (startTime != null && endTime != null) {
				int hour = Integer.valueOf(startTime.split(":")[0]);
				String tempStartTime = date + " " + startTime + ":00";
				String tempEndTime = date + " " + endTime + ":00";
				long minute = DateUtil.getMinDistanceTimes(tempStartTime, tempEndTime);
				if (Math.abs(minute) <= 5 && hour <= 13) {
					endTime = null;
				} else if (Math.abs(minute) <= 5 && hour > 13) {
					startTime = null;
				}
			}			
			//System.out.println(dept + " " + no + " " + name + " " + date + " " + startTime + " " + endTime);
			Map<String, Object> resultMap = Maps.newHashMap();
			resultMap.put("dept", dept);
			resultMap.put("no", no);
			resultMap.put("name", name);
			resultMap.put("date", date);
			resultMap.put("startTime", startTime);
			resultMap.put("endTime", endTime);
			//System.out.println(resultMap);
			lists.add(resultMap);
			
			Map<String, String> personMap = Maps.newHashMap();
			personMap.put("dept", dept);
			personMap.put("name", name);
			personMap.put("no", no);
			noSet.add(personMap);
		}

		int days = DateUtil.getMonthLastDay(year, month);
		List<Map<String, Object>> resultList = Lists.newArrayList();
		for (Map<String, String> person : noSet) { //人员迭代
			String no = person.get("no");
			String dept = person.get("dept");
			String name = person.get("name");
			//System.out.println(person.get("no") + " " + person.get("name"));

			for (int i = 1; i <= days; i++) { //日期迭代
				Map<String, Object> resultMap = Maps.newHashMap();
				resultMap.put("no", no); //员工编号
				resultMap.put("dept", dept); //员工编号
				resultMap.put("name", name); //员工姓名
				DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");        
				
				//System.out.println(i);
				String bDate = year + "-" + month + "-" + i; 
				Date bdate = format1.parse(bDate); 
				String dayDate = format1.format(bdate);
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(bdate);
			    resultMap.put("date", bDate); //日期
			    boolean isHoliday = sysAllHolidayService.isHoliday(dayDate); //默认是工作日
			    if (!isHoliday) {
				    
			    	boolean flag = true; //默认缺勤
			    	for (Map<String, Object> map : lists) { //数据迭代
			    		String mapNo = (String)map.get("no");
			    		String date = (String)map.get("date");
			    		String startTime = (String)map.get("startTime");
			    		String endTime = (String)map.get("endTime");
						
			    		Date myDate = format1.parse(date);
			    		date = format1.format(myDate);
			    		if (mapNo.equals(no) && date.equals(dayDate)) {
			    			
			    			resultMap.put("dept", dept); //部门
			    			resultMap.put("name", name); //时间
			    			
			    			flag = false; //非缺勤
			    			
			    			if (startTime == null) {
			    				resultMap.put("status", "2"); //上午未打卡
			    				resultMap.put("lateDate", dayDate); //迟到日期
			    			} else {
			    				String wholeStart = bDate + " " + startTime + ":00";
				    			//开始时间和上班时间对比
				    			String tempworkStartTime = bDate + workStartTime;
				    			long minute = DateUtil.getMinDistanceTimes(tempworkStartTime, wholeStart);
				    			if (minute < 0) { //迟到
				    				resultMap.put("status", "4"); //迟到
				    				resultMap.put("lateMinute", Math.abs(minute)); //迟到
				    				resultMap.put("lateDate", dayDate); //迟到日期
				    			}			    				
			    			}
			    			if (endTime == null) {
			    				resultMap.put("status", "3"); //下午未打卡
			    				resultMap.put("earlyDate", dayDate); //早退日期
			    			} else {
			    				String wholeEnd = bDate + " " + endTime + ":00";
				    			//接收时间和下班时间对比
				    			String tempworkEndTime = bDate + workEndTime; 
				    			long minute = DateUtil.getMinDistanceTimes(tempworkEndTime, wholeEnd);
				    			if (minute > 0) { //早退
				    				resultMap.put("status", "5"); //早退
				    				resultMap.put("earlyMinute", Math.abs(minute)); //早退
				    				resultMap.put("earlyDate", dayDate); //早退日期
				    			}				    				
			    			}
			    		}
			    	}
			    	if (flag) {
			    		resultMap.put("status", "0"); //缺勤
			    		resultMap.put("lostDate", dayDate); //缺勤日期
			    	} else if (resultMap.get("status") == null)  {
			    		resultMap.put("status", "6"); //正常
			    	}
			    	if (!"6".equals(resultMap.get("status"))) {
			    		resultList.add(resultMap);
			    	}
			    	
			    }

			}			
		}
		
		for (Map<String, Object> map : resultList) {
			System.out.println(map);
		}
		return resultList;
	}

}
