package beans.support;

import beans.config.BeanDefinition;
import beans.config.BeanDefinitionRegistry;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstrctAutowriteCapableBeanFactory implements BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    protected BeanDefinition getBeanDefinition(String name) {
        return beanDefinitionMap.get(name);
    }


    @Override
    public void registryBeanDefinition(String name, BeanDefinition bd) {
        this.beanDefinitionMap.put(name,bd);
    }
}
