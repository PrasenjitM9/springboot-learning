package cn.bocon.service.impl;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.bocon.common.DateUtil;
import cn.bocon.entity.CardData;
import cn.bocon.entity.Emp;
import cn.bocon.service.ICardDataService;
import cn.bocon.service.IEmpService;
import cn.bocon.service.IFileService;
import cn.bocon.service.ISysAllHolidayService;
import net.sf.jxls.reader.ReaderBuilder;
import net.sf.jxls.reader.XLSReader;

@Service
public class FileServiceImpl implements IFileService {
	@Autowired
	private ICardDataService cardDataService;
	@Autowired
	private IEmpService empService;
	@Autowired
	private ISysAllHolidayService sysAllHolidayService;

	/**
	 * 处理excel数据
	 */
	@Override
	@Transactional
	public void handleData(File file) throws Exception {
		InputStream inputXML = null;
		XLSReader mainReader = null;
		InputStream inputXLS = null;
		inputXML = new BufferedInputStream(getClass().getResourceAsStream("/templates/CardData.xml"));
		mainReader = ReaderBuilder.buildFromXML(inputXML);
		inputXLS = new BufferedInputStream(new FileInputStream(file));
		List<CardData> datas = Lists.newArrayList();
		Map<String, Object> beans = Maps.newHashMap();
		beans.put("datas", datas);
		mainReader.read(inputXLS, beans);
		System.out.println(beans);
		cardDataService.delete(null);
		cardDataService.insertBatch(datas);
		
		empService.delete(null);
		//去重
		Set<Emp> sets = Sets.newHashSet();

		for (CardData cardData : datas) {
			Emp emp = new Emp();
			emp.setId(cardData.getUserId());
			emp.setName(cardData.getName());
			emp.setCreateTime(new Date());
			sets.add(emp);
		}
		List<Emp> emps = Lists.newArrayList();
		emps.addAll(sets);
		if (emps != null && emps.size() > 0) {
			empService.insertBatch(emps);
		}
	}

	@Override
	public List<Map<String, Object>> export() throws Exception {
		// 处理数据
		CardData cardData = cardDataService.selectOne(null);
		Date cardTime = cardData.getCardTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(cardTime);
		
		String begin = " 00:00:00";
		String end = " 23:59:59";
		int year = calendar.get(Calendar.YEAR); //年度
		int month = calendar.get(Calendar.MONTH) + 1; //月份
		Wrapper<Emp> wrapper = new EntityWrapper<Emp>();
		wrapper.orderBy("id+0");
		List<Emp> emps = empService.selectList(wrapper);
		int days = DateUtil.getMonthLastDay(year, month);
		for (Emp emp : emps) {
			String id = emp.getId(); //用户id
			for (int i = 1; i <= days; i++) { //日期迭代
				String bDate = year + "-" + month + "-" + i; 
				Date beginTime = DateUtil.stringToDate(bDate + begin, "yyyy-MM-dd HH:mm:ss");
				Date endTime = DateUtil.stringToDate(bDate + end, "yyyy-MM-dd HH:mm:ss");
				boolean isHoliday = sysAllHolidayService.isHoliday(bDate); //默认是工作日
				if (isHoliday == false) {
					List<CardData> cardDatas = cardDataService.selectByUserIdAndTime(id, beginTime, endTime);
					//如果有3条记录，如掉中间的记录
					if (cardDatas != null && cardDatas.size() >= 3) {
						for (int j = 1; j < cardDatas.size() - 1; j++) {
							cardDataService.deleteById(cardDatas.get(j).getId());
						}
						CardData first = cardDatas.get(0);
						CardData last = cardDatas.get(cardDatas.size() - 1);
						//上午
						Date firtCardTime = first.getCardTime();
						Date lastCardTime = last.getCardTime();
						
						Calendar firstcalendar = Calendar.getInstance();
						firstcalendar.setTime(lastCardTime);
						int beginHour = firstcalendar.get(Calendar.HOUR_OF_DAY);
						
						Calendar lastcalendar = Calendar.getInstance();
						lastcalendar.setTime(lastCardTime);
						int lastHour = lastcalendar.get(Calendar.HOUR_OF_DAY);					
						
						long minute = DateUtil.getMinDistanceTimes(DateUtil.formatDateTime(firtCardTime), DateUtil.formatDateTime(lastCardTime));
						if (Math.abs(minute) <= 5 && lastHour < 13) { //上午
							cardDataService.deleteById(last.getId());
						} else if (Math.abs(minute) <= 5 && beginHour > 12) { //下午
							cardDataService.deleteById(first.getId());
						}
						
					} else if (cardDatas != null && cardDatas.size() == 2) {
						CardData first = cardDatas.get(0);
						CardData last = cardDatas.get(1);	
						Date firtCardTime = first.getCardTime();
						Date lastCardTime = last.getCardTime();		
						Calendar firstcalendar = Calendar.getInstance();
						firstcalendar.setTime(lastCardTime);
						int beginHour = firstcalendar.get(Calendar.HOUR_OF_DAY);
						
						Calendar lastcalendar = Calendar.getInstance();
						lastcalendar.setTime(lastCardTime);
						int lastHour = lastcalendar.get(Calendar.HOUR_OF_DAY);						
						long minute = DateUtil.getMinDistanceTimes(DateUtil.formatDateTime(firtCardTime), DateUtil.formatDateTime(lastCardTime));
						if (Math.abs(minute) <= 5 && lastHour < 13) { //上午
							cardDataService.deleteById(last.getId());
						} else if (Math.abs(minute) <= 5 && beginHour > 12) { //下午
							cardDataService.deleteById(first.getId());
						}						
					}
				}
			}
		}
		List<Map<String, Object>> datas = dealWith(emps, year, month, days);
		return datas;
	}
	
	private List<Map<String, Object>> dealWith(List<Emp> emps, int year, int month, int days) {
		String begin = " 00:00:00";
		String end = " 23:59:59";
		String workStartTimeStr = " 08:45:00"; //上班时间
		String workEndTimeSAtr = " 17:45:00"; //下班时间
		List<Map<String, Object>> resultList = Lists.newArrayList();
		//处理考勤记录
		for (Emp emp : emps) {
			String id = emp.getId(); //用户id
			String name = emp.getName(); //雇员名称
			for (int i = 1; i <= days; i++) { //日期迭代
				String bDate = year + "-" + month + "-" + i; 
				Date beginTime = DateUtil.stringToDate(bDate + begin, "yyyy-MM-dd HH:mm:ss"); //一天的开始时间
				Date endTime = DateUtil.stringToDate(bDate + end, "yyyy-MM-dd HH:mm:ss"); //一天的结束时间
				String workStartTime = bDate + workStartTimeStr;
				String workEndTime = bDate + workEndTimeSAtr;
				
				Date date = DateUtil.stringToDate(bDate, DateUtil.DATE_FORMAT);
				bDate = DateUtil.formatDate(date);
				
				boolean isHoliday = sysAllHolidayService.isHoliday(bDate); //默认是工作日
				if (isHoliday == false) {
					List<CardData> cardDatas = cardDataService.selectByUserIdAndTime(id, beginTime, endTime);
					Map<String, Object> resultMap = Maps.newHashMap();
					resultMap.put("no", id);
					resultMap.put("name", name);
					resultMap.put("date", bDate); //日期
					if (cardDatas == null || cardDatas.size() == 0) {
			    		resultMap.put("status", "0"); //缺勤
			    		resultMap.put("lostDate", bDate); //缺勤日期
					}
					if (cardDatas.size() == 1) {
						CardData cardData = cardDatas.get(0);
						Date cardTime = cardData.getCardTime();
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(cardTime);
						int hour = calendar.get(Calendar.HOUR_OF_DAY);
						String cardTimeStr = DateUtil.dateToString(cardTime, DateUtil.DATETIME_FORMAT);
						if (hour < 13) { //上午打卡了 下午没打
							resultMap.put("status", "3");
							resultMap.put("earlyDate", bDate); //早退日期
			    			//开始时间和上班时间对比
			    			
			    			long minute = DateUtil.getMinDistanceTimes(workStartTime, cardTimeStr);	
			    			if (minute < 0) { //迟到
			    				resultMap.put("status", "4"); //迟到
			    				resultMap.put("lateMinute", Math.abs(minute)); //迟到
			    				resultMap.put("lateDate", bDate); //迟到日期
			    			}	
						} else { //下午打卡 上午没打
							resultMap.put("status", "2"); 
							resultMap.put("lateDate", bDate); 
							
							long minute = DateUtil.getMinDistanceTimes(workEndTime, cardTimeStr);	
							if (minute > 0) {
			    				resultMap.put("status", "5"); //早退
			    				resultMap.put("earlyMinute", Math.abs(minute)); //早退
			    				resultMap.put("earlyDate", bDate); //早退日期								
							}
						}
					}
					if (cardDatas.size() == 2) {
						//计算上午
						CardData amCardData = cardDatas.get(0);
						Date amCardTime = amCardData.getCardTime();
						String wholeStart = DateUtil.dateToString(amCardTime, DateUtil.DATETIME_FORMAT);
						long minute = DateUtil.getMinDistanceTimes(workStartTime, wholeStart);
		    			if (minute < 0) { //迟到
		    				resultMap.put("status", "4"); //迟到
		    				resultMap.put("lateMinute", Math.abs(minute)); //迟到
		    				resultMap.put("lateDate", bDate); //迟到日期
		    			}							
						
		    			//计算下午
						CardData pmCardData = cardDatas.get(1);
						Date pmCardTime = pmCardData.getCardTime();		
						String wholeEnd = DateUtil.dateToString(pmCardTime, DateUtil.DATETIME_FORMAT);
						long minute2 = DateUtil.getMinDistanceTimes(workEndTime, wholeEnd);
		    			if (minute2 > 0) { //早退
		    				resultMap.put("status", "5"); //早退
		    				resultMap.put("earlyMinute", Math.abs(minute)); //早退
		    				resultMap.put("earlyDate", bDate); //早退日期
		    			}						
					}
					if (resultMap.get("status") != null) {
						resultList.add(resultMap);
					}
				}
			}
		}	
		return resultList;
	}
}
