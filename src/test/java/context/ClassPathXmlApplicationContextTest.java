package context;

import bean.Car;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ClassPathXmlApplicationContextTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Car car = (Car) classPathXmlApplicationContext.getBean("car");
        System.out.println(car);
    }
}
