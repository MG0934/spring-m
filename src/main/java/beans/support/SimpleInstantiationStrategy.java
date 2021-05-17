package beans.support;

import beans.config.BeanDefinition;
import beans.exception.BeansException;

/**
 *
 * 简单类型实例化
 *
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) {

        if(beanDefinition!=null){
            Class beanClass = beanDefinition.getBeanClass();
            if(beanClass!=null){
                try {
                    return beanClass.newInstance();
                } catch (InstantiationException | IllegalAccessException e) {
                    throw new BeansException(e);
                }
            }else{
                throw new BeansException("beanClass not found");
            }
        }

        throw new BeansException("beanDefinition not found");
    }
}
