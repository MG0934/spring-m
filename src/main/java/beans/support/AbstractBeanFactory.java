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
        Object bean = createBean(bd);
        //保存返回
        setSingleton(name,bean);

        return bean;
    }

    protected abstract BeanDefinition getBeanDefinition(String name);

    protected abstract Object createBean(BeanDefinition bd);
}
