package beans.support;

import beans.BeanFactory;
import beans.config.BeanDefinition;

public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
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

    protected abstract BeanDefinition getBeanDefinition(String name);

    protected abstract Object createBean(String beanName,BeanDefinition bd);
}
