package com.yuqiyu.chapter.hengboy.springboot.nacos.config;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yuqiyu
 */
@SpringBootApplication
@NacosPropertySource(dataId = "hengboy-spring-boot-nacos-config", autoRefreshed = true)
public class HengboySpringBootNacosConfigApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(HengboySpringBootNacosConfigApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HengboySpringBootNacosConfigApplication.class, args);
        logger.info("「「「「「Nacos config example start successfully.」」」」」");
    }

}
