import beans.config.BeanDefinition;
import beans.support.DefaultListableBeanFactory;
import beans.utils.PropertyValue;
import beans.utils.PropertyValues;
import org.junit.Test;

public class PropertyValuesTest {

    @Test
    public void testpv(){
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue2 = new PropertyValue("name","小花");
        PropertyValue propertyValue1 = new PropertyValue("age","21");
        propertyValues.addPropertyValue(propertyValue2);
        propertyValues.addPropertyValue(propertyValue1);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registryBeanDefinition("person",beanDefinition);
        Person person = (Person) defaultListableBeanFactory.getBean("person");
        System.out.println(person);
    }
}
