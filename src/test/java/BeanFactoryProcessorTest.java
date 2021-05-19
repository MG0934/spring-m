import bean.Person;
import common.CustomBeanFactoryProcessor;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

public class BeanFactoryProcessorTest {

    @Test
    public void testBeanFactoryProcessor(){
        //BeanFactoryProcessor 主要所有BeanDefintion加载完成后，但在bean实例化之前，修改BeanDefinition的属性值
        //Bean实例化 主要是在getBean的阶段
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryProcessor customBeanFactoryProcessor = new CustomBeanFactoryProcessor();
        customBeanFactoryProcessor.postProcessBeanFactory(defaultListableBeanFactory);

        Person person = (Person) defaultListableBeanFactory.getBean("person");

        System.out.println(person);
    }

}
