package com.real_estate.llk_server_spring.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LlkLoggingAspect {
    private static final Logger logger = LogManager.getLogger(LlkLoggingAspect.class);

    @AfterThrowing(pointcut = "within(com.real_estate.llk_server_spring..*)", throwing = "exception")
    public void logAfterThrowing(Exception exception) {
        logger.error("An error occurred: ", exception);
    }
}
