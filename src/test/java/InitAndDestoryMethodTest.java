import bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitAndDestoryMethodTest {

    @Test
    public void test(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Person person = (Person) classPathXmlApplicationContext.getBean("person");
        System.out.println(person);
        classPathXmlApplicationContext.registerShutdownHook();
    }
}
