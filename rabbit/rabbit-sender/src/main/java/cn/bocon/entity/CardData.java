package cn.bocon.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 
 * </p>
 *
 * @author wj
 * @since 2018-06-21
 */
@TableName("card_data")
public class CardData extends Model<CardData> {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识
     */
	private String id;
    /**
     * 用户id
     */
	private String userId;	
    /**
     * 姓名
     */
	private String name;
    /**
     * 打卡时间
     */
	@TableField("card_time")
	private Date cardTime;


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

	public Date getCardTime() {
		return cardTime;
	}

	public void setCardTime(Date cardTime) {
		this.cardTime = cardTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "CardData [id=" + id + ", userId=" + userId + ", name=" + name + ", cardTime=" + cardTime + "]";
	}

}
