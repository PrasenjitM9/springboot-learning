package cn.bocon.service.impl;

import cn.bocon.entity.CardData;
import cn.bocon.mapper.CardDataMapper;
import cn.bocon.service.ICardDataService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wj
 * @since 2018-06-21
 */
@Service
public class CardDataServiceImpl extends ServiceImpl<CardDataMapper, CardData> implements ICardDataService {

	@Override
	public List<CardData> selectByUserIdAndTime(String userId, Date beginTime, Date endTime) {
		Wrapper<CardData> wrapper = new EntityWrapper<CardData>();
		wrapper.eq("user_id", userId);
		wrapper.between("card_time", beginTime, endTime);
		wrapper.orderBy("card_time");
		List<CardData> cardDatas = this.selectList(wrapper);
		return cardDatas;
	}

}
