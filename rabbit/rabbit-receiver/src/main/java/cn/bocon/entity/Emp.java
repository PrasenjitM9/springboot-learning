package cn.bocon.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;

/**
 * <p>
 * 
 * </p>
 *
 * @author wj
 * @since 2018-06-22
 */
public class Emp extends Model<Emp> {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Emp) {
			Emp emp = (Emp)obj;
			return id.equals(emp.getId());
		}
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Emp{" + "id=" + id + ", name=" + name + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "}";
	}
}
