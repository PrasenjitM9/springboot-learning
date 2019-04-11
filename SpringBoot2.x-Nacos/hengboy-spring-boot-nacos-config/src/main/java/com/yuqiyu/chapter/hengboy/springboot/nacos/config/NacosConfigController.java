package com.yuqiyu.chapter.hengboy.springboot.nacos.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-02-28 15:35
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengyuboy
 */
@RestController
@RequestMapping(value = "/config")
public class NacosConfigController {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(NacosConfigController.class);
    /**
     * 注入nacos 配置信息
     */
    @NacosValue(value = "${chapter.name:}", autoRefreshed = true)
    private String chapterName;

    /**
     * 返回nacos 配置信息
     *
     * @return
     */
    @GetMapping(value = "/get")
    public String get() {
        return this.chapterName;
    }
}
