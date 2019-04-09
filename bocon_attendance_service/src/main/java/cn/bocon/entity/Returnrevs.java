package cn.bocon.entity;

import com.baomidou.mybatisplus.annotations.TableId;
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
public class Returnrevs extends Model<Returnrevs> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String cn;
	private String mnnum;
	private String qnexertn;
	private String qn;
	private String flag;
	private String exresult;
	private String upvalue;
	private String lowvalue;
	private String upstr;
	private String lowstr;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCn() {
		return cn;
	}

	public void setCn(String cn) {
		this.cn = cn;
	}

	public String getMnnum() {
		return mnnum;
	}

	public void setMnnum(String mnnum) {
		this.mnnum = mnnum;
	}

	public String getQnexertn() {
		return qnexertn;
	}

	public void setQnexertn(String qnexertn) {
		this.qnexertn = qnexertn;
	}

	public String getQn() {
		return qn;
	}

	public void setQn(String qn) {
		this.qn = qn;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getExresult() {
		return exresult;
	}

	public void setExresult(String exresult) {
		this.exresult = exresult;
	}

	public String getUpvalue() {
		return upvalue;
	}

	public void setUpvalue(String upvalue) {
		this.upvalue = upvalue;
	}

	public String getLowvalue() {
		return lowvalue;
	}

	public void setLowvalue(String lowvalue) {
		this.lowvalue = lowvalue;
	}

	public String getUpstr() {
		return upstr;
	}

	public void setUpstr(String upstr) {
		this.upstr = upstr;
	}

	public String getLowstr() {
		return lowstr;
	}

	public void setLowstr(String lowstr) {
		this.lowstr = lowstr;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
