import bean.Person;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Before;
import org.junit.Test;

public class BeanDefinitionAndRegisteryTest {

    private Person person = null;

    @Test
    public void testBeanDefinitionAndRegistery(){
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(Person.class);
        bf.registryBeanDefinition("person",beanDefinition);
        Person person = (Person) bf.getBean("person");
        System.out.println(person);

    }

    @Before
    public void init(){
        person = new Person("小黑", 16);
    }
}
