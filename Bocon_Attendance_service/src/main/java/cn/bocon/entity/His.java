package cn.bocon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
@TableName("his_88888880000001")
public class His extends Model<His> {

    private static final long serialVersionUID = 1L;

	@TableId(value="IDS", type= IdType.AUTO)
	private Long ids;
	@TableField("CN")
	private String cn;
	@TableField("CODE")
	private String code;
	@TableField("DATATIME")
	private Date datatime;
	@TableField("MAXVAL")
	private Double maxval;
	@TableField("MINVAL")
	private Double minval;
	@TableField("SUMVAL")
	private Double sumval;
	@TableField("AVGVAL")
	private Double avgval;
	@TableField("MNNUM")
	private String mnnum;
	@TableField("MARK")
	private String mark;
	@TableField("UNITTIME")
	private String unittime;
	@TableField("CREATE_TIME")
	private Date createTime;
	@TableField("UPDATE_TIME")
	private Date updateTime;


	public Long getIds() {
		return ids;
	}

	public void setIds(Long ids) {
		this.ids = ids;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDatatime() {
		return datatime;
	}

	public void setDatatime(Date datatime) {
		this.datatime = datatime;
	}

	public Double getMaxval() {
		return maxval;
	}

	public void setMaxval(Double maxval) {
		this.maxval = maxval;
	}

	public Double getMinval() {
		return minval;
	}

	public void setMinval(Double minval) {
		this.minval = minval;
	}

	public Double getSumval() {
		return sumval;
	}

	public void setSumval(Double sumval) {
		this.sumval = sumval;
	}

	public Double getAvgval() {
		return avgval;
	}

	public void setAvgval(Double avgval) {
		this.avgval = avgval;
	}

	public String getMnnum() {
		return mnnum;
	}

	public void setMnnum(String mnnum) {
		this.mnnum = mnnum;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getUnittime() {
		return unittime;
	}

	public void setUnittime(String unittime) {
		this.unittime = unittime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.ids;
	}

}
