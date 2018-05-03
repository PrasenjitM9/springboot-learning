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
 * @since 2018-10-27
 */
@Service
public class SysAllHolidayServiceImpl extends ServiceImpl<SysAllHolidayMapper, SysAllHoliday> implements ISysAllHolidayService {
	public void insertHoliday() throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = sdf.parse("2018-01-01");//开始时间
        Date end = sdf.parse("2018-12-31");//结束时间
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
        
        holidays.add(new SysAllHoliday("元旦节", "2018-01-01"));
        
        holidays.add(new SysAllHoliday("春节", "2018-02-13")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-14")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-15")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-16")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-17")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-18")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-19")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-20")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-21")); 
        holidays.add(new SysAllHoliday("春节", "2018-02-22")); 
        
        holidays.add(new SysAllHoliday("清明节", "2018-04-05"));   
        holidays.add(new SysAllHoliday("清明节", "2018-04-06"));   
        holidays.add(new SysAllHoliday("清明节", "2018-04-07"));   
        
        holidays.add(new SysAllHoliday("劳动节", "2018-04-29"));
        holidays.add(new SysAllHoliday("劳动节", "2018-04-30"));
        holidays.add(new SysAllHoliday("劳动节", "2018-05-01"));
        
        holidays.add(new SysAllHoliday("端午节", "2018-06-16"));
        holidays.add(new SysAllHoliday("端午节", "2018-06-17"));
        holidays.add(new SysAllHoliday("端午节", "2018-06-18"));
        
        holidays.add(new SysAllHoliday("中秋节", "2018-09-22"));    
        holidays.add(new SysAllHoliday("中秋节", "2018-09-23"));   
        holidays.add(new SysAllHoliday("中秋节", "2018-09-24"));   
        
        holidays.add(new SysAllHoliday("国庆节", "2018-10-01"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-02"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-03"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-04"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-05"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-06"));
        holidays.add(new SysAllHoliday("国庆节", "2018-10-07"));
        
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
        workDays.add(new SysAllHoliday("补班", "2018-02-11")); //补春节
        workDays.add(new SysAllHoliday("补班", "2018-02-24")); //补春节
        
        workDays.add(new SysAllHoliday("补班", "2018-04-08")); //补清明节
        
        workDays.add(new SysAllHoliday("补班", "2018-04-28"));
        
        workDays.add(new SysAllHoliday("补班", "2018-09-29"));
        workDays.add(new SysAllHoliday("补班", "2018-09-30"));
        
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
