package beans.config;

import beans.config.BeanDefinition;

public interface BeanDefinitionRegistry {

    void registryBeanDefinition(String name, BeanDefinition bd);

}
