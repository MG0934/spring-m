import event.CustomEvent;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventAndEventListerTest {

    @Test
    public void test(){

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));
        applicationContext.registerShutdownHook();//或者applicationContext.close()主动关闭容器;

    }
}
