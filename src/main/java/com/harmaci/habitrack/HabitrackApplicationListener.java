package com.harmaci.habitrack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * An ApplicationListener that logs the request endpoints
 */
public class HabitrackApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger logger
            = LoggerFactory.getLogger(HabitrackApplicationListener.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ApplicationContext applicationContext = event.getApplicationContext();
        RequestMappingHandlerMapping requestMappingHandlerMapping = applicationContext
                .getBean("requestMappingHandlerMapping", RequestMappingHandlerMapping.class);

        requestMappingHandlerMapping
                .getHandlerMethods()
                .forEach((key, value) -> logger.info("{} {}", key, value));
    }
}
