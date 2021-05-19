package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.BeansException;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * 简单类型实例化
 *
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {

        if(beanDefinition!=null){
            Class beanClass = beanDefinition.getBeanClass();
            if(beanClass!=null){
                try {
                    Constructor declaredConstructor = beanClass.getDeclaredConstructor();
                    return declaredConstructor.newInstance();
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw new BeansException(e);
                }
            }else{
                throw new BeansException("beanClass not found");
            }
        }

        throw new BeansException("beanDefinition not found");
    }
}
