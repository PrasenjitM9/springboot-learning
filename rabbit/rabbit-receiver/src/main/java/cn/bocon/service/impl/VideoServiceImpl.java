package cn.bocon.service.impl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import cn.bocon.Test;
import cn.bocon.service.IVideoService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
@Service
public class VideoServiceImpl implements IVideoService {

	@Override
	public void add() {
		String[] str = null;
		try {
			Test.main(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
