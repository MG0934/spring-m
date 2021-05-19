import bean.Car;
import bean.Person;
import beans.exception.BeansException;
import beans.support.DefaultListableBeanFactory;
import beans.xml.XmlBeanDefinitionReader;
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
