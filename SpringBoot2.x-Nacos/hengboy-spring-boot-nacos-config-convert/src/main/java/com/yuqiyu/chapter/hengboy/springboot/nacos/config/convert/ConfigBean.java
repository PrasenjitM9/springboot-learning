package com.yuqiyu.chapter.hengboy.springboot.nacos.config.convert;

import lombok.Data;

/**
 * 配置实体
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-02-28 17:24
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengyuboy
 */
@Data
public class ConfigBean {
    private String name;
    private String ip;
    private int port;
}
