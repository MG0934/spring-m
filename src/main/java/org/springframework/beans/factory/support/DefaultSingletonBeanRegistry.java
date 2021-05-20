package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * 实例bean存储处
     */
    private final Map<String,Object> beanMap =  new HashMap<>();

    /**
     * 销毁的Bean存放的地方
     */
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return beanMap.get(name);
    }

    public void setSingleton(String name,Object bean){
        beanMap.put(name,bean);
    }

    public void registerDisposableBean(String beanName,DisposableBean bean){
        disposableBeans.put(beanName, bean);
    };

    public void destroySingletons(){
        Set<String> beanNames = disposableBeans.keySet();
        for (String beanName : beanNames) {
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            }catch (Exception e){
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }
}
