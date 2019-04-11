package cn.bocon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testlogging")
public class LoggingTestController {
    private static Logger logger = LoggerFactory.getLogger(LoggingTestController.class);
    @GetMapping("/hello")
    public String hello() {
        for(int i=0;i<10_0000;i++){
            logger.info("info execute index method");
            logger.warn("warn execute index method");
            logger.error("error execute index method");
        }
        return "My First SpringBoot Application";
    }
}
