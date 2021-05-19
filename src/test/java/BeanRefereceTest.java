import bean.Car;
import bean.Person;
import beans.config.BeanDefinition;
import beans.config.BeanReferece;
import beans.support.DefaultListableBeanFactory;
import beans.utils.PropertyValue;
import beans.utils.PropertyValues;
import org.junit.Test;

public class BeanRefereceTest {

    @Test
    public  void testBeanReferece(){
        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue2 = new PropertyValue("name","小花");
        PropertyValue propertyValue1 = new PropertyValue("age","21");
        propertyValues.addPropertyValue(propertyValue2);
        propertyValues.addPropertyValue(propertyValue1);
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);

        PropertyValues propertyValues1 = new PropertyValues();
        propertyValues1.addPropertyValue(new PropertyValue("person",new BeanReferece("person")));
        BeanDefinition beanDefinition1 = new BeanDefinition(Car.class, propertyValues1);

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.registryBeanDefinition("person",beanDefinition);
        defaultListableBeanFactory.registryBeanDefinition("car",beanDefinition1);
        Car car = (Car) defaultListableBeanFactory.getBean("car");
        car.driver();
    }
}
