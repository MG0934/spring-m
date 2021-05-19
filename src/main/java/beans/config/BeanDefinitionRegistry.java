package beans.config;

import beans.config.BeanDefinition;
import beans.exception.BeansException;

public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注入BeanDefinition
     *
     * @param name
     * @param bd
     */
    void registryBeanDefinition(String name, BeanDefinition bd);

    /**
     * 根据名称查找BeanDefinition
     * @param beanName
     * @return
     * @throws BeansException
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


    /**
     * 是否包含指定名称的BeanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);


    /**
     * 返回定义的所有的bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
