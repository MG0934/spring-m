package aop;

import aop.bean.WorldService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AutoProxyTest {

    @Test
    public void test() {

        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        //获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();

    }
}
