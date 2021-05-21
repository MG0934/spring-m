package org.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import cn.hutool.core.bean.BeanUtil;

import java.lang.reflect.Method;
import java.util.List;

public abstract class AbstrctAutowriteCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /**
     * 默认初始化 简单实例化策略
     */
    InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(String beanName, BeanDefinition bd) {

        //如果bean需要代理，则直接返回代理对象
        Object bean = resolveBeforeInstantiation(beanName, bd);
        if (bean != null) {
            return bean;
        }

        return doCreateBean(beanName, bd);
    }

    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition bd){

        Object bean = applyBeanPostProcessorsBeforeInstantiation(bd.getBeanClass(),beanName);

        if(bean!=null){
            return applyBeanPostProcessorsBeforeInitialization(bean,beanName);
        }

        return null;
    }

    protected Object applyBeanPostProcessorsBeforeInstantiation(Class beanClass, String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessor()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor) {
                Object result = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(beanClass, beanName);
                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    };

    ;

    protected Object doCreateBean(String beanName, BeanDefinition bd) {
        //实例化
        Object bean = null;
        try {
            //实例化对象
            bean = createBeanInstance(bd);
            //属性填充
            applyPropertyValues(beanName, bean, bd);
            //属性填充好了之后 调用init方法
            //执行bean的初始化方法 和 BeanPostProcessor的前置和后置方法
            initializeBean(beanName, bean, bd);
        } catch (BeansException exception) {
            throw new BeansException("Instantiation of bean failed", exception);
        }

        //注册有销毁方法得bean
        registerDisposableBeanIfNecessary(beanName, bean, bd);
        //添加到singleton
        if(bd.isSingleton()){
            addSingleton(beanName, bean);
        }
        return bean;
    }

    /**
     * 注册有销毁方法得Bean 即bean继承自disposable或者自定义得销毁方法
     *
     * @param beanName
     * @param bean
     * @param bd
     */
    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition bd) {

        if (bean instanceof DisposableBean || StrUtil.isNotEmpty(bd.getDestoryMethodName())) {
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, bd));
        }

    }

    ;

    protected Object initializeBean(String beanName, Object bean, BeanDefinition bd) {
        //根据感知器注入
        if(bean instanceof BeanFactoryAware){
            ((BeanFactoryAware) bean).setBeanFactory(this);
        }

        //执行BeanPostBeforeProcessor的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        //初始化对象
        invokeInitMethods(beanName, wrappedBean, bd);

        //执行BeanPostAfterProcessor的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }


    /**
     * 创建实例化对象
     *
     * @param bd
     * @return
     */
    protected Object createBeanInstance(BeanDefinition bd) {
        return getInstantiationStrategy().instantiate(bd);
    }

    ;

    /**
     * 为Bean属性填充
     *
     * @param beanName
     * @param bean
     * @param bd
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition bd) {

        try {
            for (PropertyValue propertyValue : bd.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();

                if (value instanceof BeanReferece) {
                    String referceName = ((BeanReferece) value).getBeanName();
                    value = getBean(referceName);
                }

                BeanUtil.setFieldValue(bean, name, value);
            }

        } catch (Exception ex) {
            throw new BeansException("Error setting property values for bean:" + beanName, ex);
        }

    }

    ;

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 传入 自定义的 实例化策略
     *
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


    private void invokeInitMethods(String beanName, Object bean, BeanDefinition bd) {

        if (bean instanceof InitializingBean) {
            ((InitializingBean) bean).afterPropertiesSet();
        }

        //获取是否有init 方法
        if (StrUtil.isNotEmpty(bd.getInitMethodName())) {
            Method initMethod = ClassUtil.getPublicMethod(bd.getBeanClass(), bd.getInitMethodName());
            if (initMethod == null) {
                throw new BeansException("Could not find an init method name " + initMethod);
            }

            try {
                initMethod.invoke(bean);
            }catch (Exception ex){
                throw new BeansException("Could not find an init method name " + initMethod);
            }
        }

    }

    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;

        for (BeanPostProcessor processor : getBeanPostProcessor()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }

            result = current;
        }

        return result;
    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {

        Object result = existingBean;

        for (BeanPostProcessor processor : getBeanPostProcessor()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
