package beans.support;

import beans.config.BeanDefinition;

public interface InstantiationStrategy {

    Object instantiate (BeanDefinition beanDefinition);

}
