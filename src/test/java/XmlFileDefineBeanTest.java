import bean.Car;
import bean.Person;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.junit.Test;

public class XmlFileDefineBeanTest {

    @Test
    public void testXmlFile() throws BeansException{
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(bf);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Car car = (Car) bf.getBean("car");
        System.out.println(car);

        Person person = (Person) bf.getBean("person");
        System.out.println(person);
    }

}
