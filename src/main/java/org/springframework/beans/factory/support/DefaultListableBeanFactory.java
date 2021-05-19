package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstrctAutowriteCapableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultListableBeanFactory extends AbstrctAutowriteCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> keys = beanDefinitionMap.keySet();
        return  keys.toArray(new String[keys.size()]);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {

        Map<String,T> result = new HashMap<>();

        beanDefinitionMap.forEach((beanName,BeanDefinition)->{
            Class beanClass = BeanDefinition.getBeanClass();
            if(type.isAssignableFrom(beanClass)){
                T bean = (T) getBean(beanName);
                result.put(beanName,bean);
            }
        });

        return result;
    }


    @Override
    public void registryBeanDefinition(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name,bd);
    }
}
