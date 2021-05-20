import bean.Car;
import bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanPrototypeTest {

    @Test
    public void testScopePrototype(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Person car1 = applicationContext.getBean("person", Person.class);
        Person car2 = applicationContext.getBean("person", Person.class);
        System.out.println(car1.hashCode());
        System.out.println(car2.hashCode());
        assertThat(car1 != car2).isTrue();
    }
}
