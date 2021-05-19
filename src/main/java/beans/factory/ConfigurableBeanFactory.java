package beans.factory;

import beans.config.SingletonBeanRegistry;
import beans.exception.BeansException;

public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    <T> T getBean(String beanName,Class<T> requiredType) throws BeansException;

}
