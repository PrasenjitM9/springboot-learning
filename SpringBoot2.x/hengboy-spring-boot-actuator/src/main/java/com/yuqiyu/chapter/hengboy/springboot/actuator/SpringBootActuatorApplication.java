package com.yuqiyu.chapter.hengboy.springboot.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot核心技术：使用Actuator监控你的应用程序
 * @author：于起宇 <p>
 * ================================
 * Created with IDEA.
 * Date：2018/10/11
 * Time：5:22 PM
 * 个人博客：http://blog.yuqiyu.com
 * 简书：http://www.jianshu.com/u/092df3f77bca
 * 码云：https://gitee.com/hengboy
 * GitHub：https://github.com/hengyuboy
 * ================================
 * </p>
 */
@SpringBootApplication
public class SpringBootActuatorApplication {
    /**
     * logger instance
     */
    static Logger logger = LoggerFactory.getLogger(SpringBootActuatorApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActuatorApplication.class, args);
        logger.info("「「「「「启动完成.」」」」」");
    }
}
