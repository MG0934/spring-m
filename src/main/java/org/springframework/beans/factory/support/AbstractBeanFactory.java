package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    @Override
    public Object getBean(String name) {
        Object singleton = getSingleton(name);
        if(singleton!=null){
            return singleton;
        }

        //创建单例对象
        //获取类定义
        BeanDefinition bd = getBeanDefinition(name);
        //创建对象
        return createBean(name,bd);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) throws BeansException {
        return (T) getBean(beanName);
    }

    protected abstract BeanDefinition getBeanDefinition(String name);

    protected abstract Object createBean(String beanName,BeanDefinition bd);
}
