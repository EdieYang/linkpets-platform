/**
 *
 */
package com.linkpets.cms.netty;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextHelper implements ApplicationContextAware {

    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextHelper.context = applicationContext;
    }

    public static Object getBean(String name) {
        return context.getBean(name);
    }

    public static Object getBean(Class clazz) {
        return context.getBean(clazz);
    }




}