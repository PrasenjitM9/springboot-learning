package com.yuqiyu.chapter.hengboy.springboot.nacos.config.convert;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.convert.NacosConfigConverter;

/**
 * 配置类json转换为configBean
 *
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-02-28 17:27
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengyuboy
 */
public class ConfigBeanConfigConvert implements NacosConfigConverter<ConfigBean> {
    @Override
    public boolean canConvert(Class<ConfigBean> targetType) {
        return true;
    }

    @Override
    public ConfigBean convert(String config) {
        return JSON.parseObject(config, ConfigBean.class);
    }
}
