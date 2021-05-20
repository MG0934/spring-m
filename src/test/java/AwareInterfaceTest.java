import bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AwareInterfaceTest {

    @Test
    public  void test(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = (Person) applicationContext.getBean("person");
        System.out.println(person);
    }
}
