package cn.bocon.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.bocon.common.DateUtil;
import cn.bocon.entity.SysAllHoliday;
import cn.bocon.mapper.SysAllHolidayMapper;
import cn.bocon.service.ISysAllHolidayService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wangjian
 * @since 2019-10-27
 */
@Service
public class SysAllHolidayServiceImpl extends ServiceImpl<SysAllHolidayMapper, SysAllHoliday> implements ISysAllHolidayService {
	public void insertHoliday() throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2019-01-01");//开始时间
        Date end = sdf.parse("2019-12-31");//结束时间
        List<Date> lists = DateUtil.dateSplit(start, end);
        
        //-------------------插入周末时间---------------
        if (!lists.isEmpty()) {
            for (Date date : lists) {
         	   Calendar cal = Calendar.getInstance();
         	    cal.setTime(date);
                if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
                {
             	   SysAllHoliday sysAllHoliday = new SysAllHoliday();
             	   sysAllHoliday.setTitle("周末");
             	   sysAllHoliday.setHolidayDate(sdf.format(date));
             	   System.out.println("插入日期:" + sdf.format(date) + ",周末");
             	   this.insert(sysAllHoliday);
                }
            }
        }
        
        //---------------插入节假日时间------------------
        List<SysAllHoliday> holidays = new ArrayList<SysAllHoliday>();
        
        holidays.add(new SysAllHoliday("元旦节", "2019-01-01"));
        
        holidays.add(new SysAllHoliday("春节", "2019-02-01")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-02")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-03")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-04")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-05")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-06")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-07")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-08")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-09")); 
        holidays.add(new SysAllHoliday("春节", "2019-02-10")); 
        
        holidays.add(new SysAllHoliday("清明节", "2019-04-05"));   
        holidays.add(new SysAllHoliday("清明节", "2019-04-06"));   
        holidays.add(new SysAllHoliday("清明节", "2019-04-07"));   
        
        holidays.add(new SysAllHoliday("劳动节", "2019-05-01"));
        
        holidays.add(new SysAllHoliday("端午节", "2019-06-07"));
        holidays.add(new SysAllHoliday("端午节", "2019-06-08"));
        holidays.add(new SysAllHoliday("端午节", "2019-06-09"));
        
        holidays.add(new SysAllHoliday("中秋节", "2019-09-13"));    
        holidays.add(new SysAllHoliday("中秋节", "2019-09-14"));   
        holidays.add(new SysAllHoliday("中秋节", "2019-09-15"));   
        
        holidays.add(new SysAllHoliday("国庆节", "2019-10-01"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-02"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-03"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-04"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-05"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-06"));
        holidays.add(new SysAllHoliday("国庆节", "2019-10-07"));
        
        for(SysAllHoliday day:holidays) {
     	   //跟周末冲突的，不重复插入
            Wrapper<SysAllHoliday> wrapper = new EntityWrapper<SysAllHoliday>();
            wrapper.eq("holiday_date", day.getHolidayDate());
            SysAllHoliday preSysAllHoliday = this.selectOne(wrapper);
            if (preSysAllHoliday == null) {
            	System.out.println("插入日期：" + day.getHolidayDate() + "," + day.getTitle());
            	this.insert(day);
            }
        }
        
        //-------------- 剔除补班时间(周末需要补班的)---------------------
        List<SysAllHoliday> workDays = new ArrayList<SysAllHoliday>();
        
        workDays.add(new SysAllHoliday("补班", "2019-09-29")); //补国庆
        workDays.add(new SysAllHoliday("补班", "2019-10-12")); //补国庆
        
        for(SysAllHoliday day:workDays) {
     	   System.out.println("剔除日期：" + day.getHolidayDate() + "," + day.getTitle());
     	   Map<String, Object> columnMap = Maps.newHashMap();
     	   columnMap.put("holiday_date", day.getHolidayDate());
     	   this.deleteByMap(columnMap);
        }
	}
	
	/**
	 * 
	* @Title: isHoliday
	* @Description: 是否是假期
	* @param date
	* @return
	 */
	public boolean isHoliday(String date) {
        Wrapper<SysAllHoliday> wrapper = new EntityWrapper<SysAllHoliday>();
        wrapper.eq("holiday_date", date);
        SysAllHoliday preSysAllHoliday = this.selectOne(wrapper);
        if (preSysAllHoliday != null) {
        	return true;
        }
        return false;
	}
}
