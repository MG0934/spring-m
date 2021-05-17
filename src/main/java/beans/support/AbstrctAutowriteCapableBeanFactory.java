package beans.support;

import beans.config.BeanDefinition;
import beans.exception.BeansException;
import beans.utils.PropertyValue;
import cn.hutool.core.bean.BeanUtil;

public abstract class AbstrctAutowriteCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 默认初始化 简单实例化策略
     */
    InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName,BeanDefinition bd) {
        return doCreateBean(beanName,bd);
    }

    protected Object doCreateBean(String beanName,BeanDefinition bd) {
        //实例化
        Object instantiate = null;
        try {
            //实例化对象
           instantiate = createBeanInstance (bd);
            //属性填充
           applyPropertyValues(beanName, instantiate, bd);
        }catch (BeansException exception){
            throw new BeansException("Instantiation of bean failed", exception);
        }

        setSingleton(beanName,instantiate);
        //添加到singleton
        return instantiate;
    }

    /**
     * 创建实例化对象
     * @param bd
     * @return
     */
    protected Object createBeanInstance(BeanDefinition bd){
        return getInstantiationStrategy().instantiate(bd);
    };

    /**
     * 为Bean属性填充
     * @param beanName
     * @param bean
     * @param bd
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition bd){

        try {
            for (PropertyValue propertyValue : bd.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                BeanUtil.setFieldValue(bean,name,value);
            }

        }catch (Exception ex){
            throw  new BeansException("Error setting property values for bean:"+beanName,ex);
        }

    };

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 传入 自定义的 实例化策略
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
