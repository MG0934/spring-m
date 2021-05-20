import bean.Car;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FactoryBeanTest {
    @Test
    public void testFactoryBean(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car1 = classPathXmlApplicationContext.getBean("car",Car.class);
        Car car2 = classPathXmlApplicationContext.getBean("car",Car.class);
        System.out.println(car1+" "+ car1.hashCode());
        System.out.println(car2+" "+ car2.hashCode());
    }
}
