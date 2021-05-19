import bean.Car;
import common.CustomBeanProcessor;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanProcessorTest {

    @Test
    public void testBeanProcessor(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        defaultListableBeanFactory.addBeanPostProcessor(new CustomBeanProcessor());
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        Car car = (Car) defaultListableBeanFactory.getBean("car");
        System.out.println(car);
    }
}
