package ioc;

import bean.HelloService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ValueAnnotaitonTest {


    @Test
    public void testValueAnnotation(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:value-annotation.xml");
        HelloService helloService = classPathXmlApplicationContext.getBean("helloService", HelloService.class);
        helloService.driver();
    }

}
