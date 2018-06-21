package cn.bocon.service.impl;

import java.util.Map;

import cn.bocon.entity.His;
import cn.bocon.mapper.HisMapper;
import cn.bocon.service.IHisService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
@Service
public class HisServiceImpl extends ServiceImpl<HisMapper, His> implements IHisService {
	@Autowired
	private HisMapper hisMapper;

	@Override
	public int findCount(Map<String, Object> map) {
		return hisMapper.findCount(map);
	}
	
}
