package com.mybatis3.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class SpringUtil {
    private static final Logger log = LoggerFactory.getLogger(SpringUtil.class);

    public static void showInfo(ApplicationContext applicationContext) {
        if (applicationContext != null) {
            log.info("Beans Count: {}", applicationContext.getBeanDefinitionCount());
            for (String beanDefinitionName : applicationContext.getBeanDefinitionNames()) {
                Object bean = applicationContext.getBean(beanDefinitionName);
                log.info("Bean name: {}\nBean Type: {},\nBean: {}", beanDefinitionName, bean.getClass(),
                        bean.toString());
            }
        } else {
            log.warn("Given ApplicationContext is NULL. Pls check!");
        }
    }
}
