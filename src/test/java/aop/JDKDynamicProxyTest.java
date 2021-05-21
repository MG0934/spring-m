package aop;

import aop.bean.WorldService;
import aop.bean.WorldServiceImpl;
import aop.bean.WorldServiceInteceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.JdkDynamicAopProxy;

public class JDKDynamicProxyTest {

    @Test
    public void testJdkDynamicProxy() throws Exception {
        //jdk动态代理 必须代理得是接口
        WorldService worldService = new WorldServiceImpl();
        //切入得支持类 TargetSource targetSource ;MethodInterceptor methodInterceptor; MethodMatcher methodMatcher; 单纯是一个存储得地方
        AdvisedSupport advisedSupport = new AdvisedSupport();
        //包装一下 目标类
        TargetSource targetSource = new TargetSource(worldService);
        //核心代理类
        WorldServiceInteceptor methodInterceptor = new WorldServiceInteceptor();
        //获得切点
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* aop.bean.WorldService.explode(..))").getMethodMatcher();

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }
}
