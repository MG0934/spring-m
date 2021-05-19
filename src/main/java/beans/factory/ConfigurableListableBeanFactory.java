package beans.factory;

import beans.config.BeanDefinition;
import beans.exception.BeansException;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory,AutowireCapableBeanFactory,ConfigurableBeanFactory{

    /**
     * 根据名字查找BeanDefinition
     *
     * @param beanName
     * @return
     * @throws BeansException 如果找不到BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName)throws BeansException;

}
