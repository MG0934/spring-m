import bean.HelloService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutowriteAnnotationTest {


    @Test
    public void testAutowrite() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:autowrited-annotation.xml");

        HelloService hello = applicationContext.getBean(HelloService.class);

        hello.driver();
        System.out.println(hello.getCar());


    }


}
