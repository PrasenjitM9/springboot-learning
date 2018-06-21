package cn.bocon.service;

import java.util.Map;

import cn.bocon.entity.His;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
public interface IHisService extends IService<His> {

	int findCount(Map<String, Object> map);
	
}
