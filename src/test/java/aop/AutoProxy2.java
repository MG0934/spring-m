package aop;

import aop.bean.WorldService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoProxy2 {

    @Test
    public void testAutoProxy() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:auto-proxy.xml");

        //获取代理对象
        WorldService worldService = applicationContext.getBean("worldService", WorldService.class);
        worldService.explode();
        WorldService worldService1 = applicationContext.getBean("worldService", WorldService.class);
        assertThat(worldService == worldService1).isTrue();
    }
}
