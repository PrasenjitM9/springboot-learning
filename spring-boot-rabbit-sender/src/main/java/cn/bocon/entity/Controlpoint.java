package cn.bocon.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 监控点信息表
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
public class Controlpoint extends Model<Controlpoint> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	@TableField("point_name")
	private String pointName;
	private String mnnum;
	private String operator;
	private String longitude;
	private String latitude;
	private String mark;
	@TableField("contains_cod")
	private String containsCod;
	private String rtdtablename;
	private String histablename;
	private String acceptance;
	@TableField("communication_mode")
	private String communicationMode;
	private String cardno;
	private Integer pointtypeid;
	private Integer monitortype;
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
	private Date updatetime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPointName() {
		return pointName;
	}

	public void setPointName(String pointName) {
		this.pointName = pointName;
	}

	public String getMnnum() {
		return mnnum;
	}

	public void setMnnum(String mnnum) {
		this.mnnum = mnnum;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getContainsCod() {
		return containsCod;
	}

	public void setContainsCod(String containsCod) {
		this.containsCod = containsCod;
	}

	public String getRtdtablename() {
		return rtdtablename;
	}

	public void setRtdtablename(String rtdtablename) {
		this.rtdtablename = rtdtablename;
	}

	public String getHistablename() {
		return histablename;
	}

	public void setHistablename(String histablename) {
		this.histablename = histablename;
	}

	public String getAcceptance() {
		return acceptance;
	}

	public void setAcceptance(String acceptance) {
		this.acceptance = acceptance;
	}

	public String getCommunicationMode() {
		return communicationMode;
	}

	public void setCommunicationMode(String communicationMode) {
		this.communicationMode = communicationMode;
	}

	public String getCardno() {
		return cardno;
	}

	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	public Integer getPointtypeid() {
		return pointtypeid;
	}

	public void setPointtypeid(Integer pointtypeid) {
		this.pointtypeid = pointtypeid;
	}

	public Integer getMonitortype() {
		return monitortype;
	}

	public void setMonitortype(Integer monitortype) {
		this.monitortype = monitortype;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
