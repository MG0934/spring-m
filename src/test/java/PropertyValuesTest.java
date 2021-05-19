import bean.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
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
