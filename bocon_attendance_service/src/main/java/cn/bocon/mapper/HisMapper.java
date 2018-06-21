package cn.bocon.mapper;

import java.util.Map;

import cn.bocon.entity.His;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author linxiaoqiang
 * @since 2017-09-18
 */
public interface HisMapper extends BaseMapper<His> {

	int findCount(Map<String, Object> map);

}