package expanding;

import bean.Car;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyPlaceholderConfigurerTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:placeholder.xml");
        Car car = (Car) classPathXmlApplicationContext.getBean("car");
        System.out.println(car);
    }
}
