package cn.bocon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
public class Returncontrol extends Model<Returncontrol> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String ip;
	private String sendcode;
	private String qn;
	private String typename;
	private String mnnum;
	private Date operatetime;
	private String operator;
	private String mark;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSendcode() {
		return sendcode;
	}

	public void setSendcode(String sendcode) {
		this.sendcode = sendcode;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getMnnum() {
		return mnnum;
	}

	public void setMnnum(String mnnum) {
		this.mnnum = mnnum;
	}

	public Date getOperatetime() {
		return operatetime;
	}

	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
