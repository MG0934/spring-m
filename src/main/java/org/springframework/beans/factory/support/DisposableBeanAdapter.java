package org.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;
import java.sql.Struct;

public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private final String destoryMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition bd) {
        this.bean = bean;
        this.beanName = beanName;
        this.destoryMethodName = bd.getDestoryMethodName();
    }

    @Override
    public void destroy() throws Exception {

        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        if (StrUtil.isNotEmpty(destoryMethodName) && !(bean instanceof DisposableBean && "destory".equalsIgnoreCase(this.destoryMethodName))) {
            //执行自定义方法
            Method destoryMethod = ClassUtil.getPublicMethod(bean.getClass(), destoryMethodName);
            if (destoryMethod == null) {
                throw new BeansException("Couldn't find a destroy method named '" + destoryMethodName + "' on bean with name '" + beanName + "'");
            }
            destoryMethod.invoke(bean);
        }

    }
}
