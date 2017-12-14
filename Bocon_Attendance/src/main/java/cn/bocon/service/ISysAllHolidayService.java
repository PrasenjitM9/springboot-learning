package cn.bocon.service;

import cn.bocon.entity.SysAllHoliday;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-10-27
 */
public interface ISysAllHolidayService extends IService<SysAllHoliday> {
	public void insertHoliday() throws Exception;
	
	public boolean isHoliday(String date);
}
