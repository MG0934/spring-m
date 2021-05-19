package common;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        BeanDefinition person = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = person.getPropertyValues();
        PropertyValue propertyValue = new PropertyValue("name", "mooxy");
        propertyValues.addPropertyValue(propertyValue);
    }
}
