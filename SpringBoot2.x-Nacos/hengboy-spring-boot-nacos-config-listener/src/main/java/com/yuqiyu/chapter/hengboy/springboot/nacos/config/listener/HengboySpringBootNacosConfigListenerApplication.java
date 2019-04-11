package com.yuqiyu.chapter.hengboy.springboot.nacos.config.listener;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.yuqiyu.chapter.hengboy.springboot.nacos.config.listener.HengboySpringBootNacosConfigListenerApplication.DATA_ID;

/**
 * Nacos Config Listener
 *
 * @author：恒宇少年 - 于起宇
 * <p>
 * DateTime：2019-03-01 10:08
 * Blog：http://blog.yuqiyu.com
 * WebSite：http://www.jianshu.com/u/092df3f77bca
 * Gitee：https://gitee.com/hengboy
 * GitHub：https://github.com/hengyuboy
 */
@SpringBootApplication
@NacosPropertySource(dataId = DATA_ID, autoRefreshed = true)
public class HengboySpringBootNacosConfigListenerApplication {
    /**
     * data-id
     */
    public final static String DATA_ID = "hengboy-spring-boot-nacos-config";

    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(HengboySpringBootNacosConfigListenerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HengboySpringBootNacosConfigListenerApplication.class, args);
        logger.info("「「「「「Nacos config listener example start successfully.」」」」」");
    }

    /**
     * 监听DATA_ID配置变动监听
     *
     * @param configContent 配置内容
     */
    @NacosConfigListener(dataId = DATA_ID)
    public void configChangeListener(String configContent) {
        logger.info("变动后的监听内容：{}", configContent);
    }
}
