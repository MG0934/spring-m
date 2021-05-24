import bean.Car;
import bean.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class PackageScanTest {


    @Test
    public void testScanPackage() throws Exception {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:package-scan.xml");
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        assertThat(person).isNotNull();
    }

}
