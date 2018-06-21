package cn.bocon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-10-27
 */
@TableName("sys_all_holiday")
public class SysAllHoliday extends Model<SysAllHoliday> {

    private static final long serialVersionUID = 1L;

	private String id;
	private String title;
	@TableField("holiday_date")
	private String holidayDate;

	public SysAllHoliday() {
	}
	
	public SysAllHoliday(String title, String holidayDate) {
		this.title = title;
		this.holidayDate = holidayDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(String holidayDate) {
		this.holidayDate = holidayDate;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
