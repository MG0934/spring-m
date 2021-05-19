package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {

    Object instantiate (BeanDefinition beanDefinition);

}
