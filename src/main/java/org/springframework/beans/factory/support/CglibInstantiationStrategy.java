package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 通过cglib 进行实例化 自行实现
 *
 */
public class CglibInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {
        //自行实现
        return null;
    }
}
