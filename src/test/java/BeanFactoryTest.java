import beans.factory.BeanFactory;
import org.junit.Test;

public class BeanFactoryTest {

    @Test
    public void testGetBean(){
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("xh",new Person("小红",12));
        Person xh = (Person) beanFactory.getBean("xh");
        System.out.println(xh);
    }
}
