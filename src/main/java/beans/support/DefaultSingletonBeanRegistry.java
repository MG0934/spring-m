package beans.support;

import beans.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private final Map<String,Object> beanMap =  new HashMap<>();

    @Override
    public Object getSingleton(String name) {
        return beanMap.get(name);
    }

    public void setSingleton(String name,Object bean){
        beanMap.put(name,bean);
    }
}
