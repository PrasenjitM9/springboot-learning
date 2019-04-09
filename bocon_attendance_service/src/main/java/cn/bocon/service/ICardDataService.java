package cn.bocon.service;

import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.bocon.entity.CardData;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wj
 * @since 2018-06-21
 */
public interface ICardDataService extends IService<CardData> {

	List<CardData> selectByUserIdAndTime(String userId, Date beginTime, Date endTime);
}
