package com.yuqiyu.chapter.hengboy.springboot.nacos.config.convert;

import com.alibaba.nacos.api.config.annotation.NacosConfigListener;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuqiyu
 */
@SpringBootApplication
@NacosPropertySource(dataId = Constants.DATA_ID, autoRefreshed = true)
public class HengboySpringBootNacosConfigConvertApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(HengboySpringBootNacosConfigConvertApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HengboySpringBootNacosConfigConvertApplication.class, args);
        logger.info("「「「「「Nacos config convert example start successfully.」」」」」");
    }

    /**
     * nacos config listener
     *
     * @param config
     */
    @NacosConfigListener(dataId = Constants.DATA_ID, converter = ConfigBeanConfigConvert.class)
    public void convertConfig(ConfigBean config) {
        logger.info("转换后的对象：{}", config);
    }
}
