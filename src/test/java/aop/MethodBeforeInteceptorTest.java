package aop;

import aop.bean.WorldService;
import aop.bean.WorldServiceImpl;
import aop.bean.WorldServiceInteceptor;
import aop.bean.WorldServiceMethodBeforeInteceptor;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;

public class MethodBeforeInteceptorTest {

    @Test
    public void proxyFactoryTest(){
        WorldService worldService = new WorldServiceImpl();
        WorldServiceMethodBeforeInteceptor worldServiceMethodBeforeInteceptor = new WorldServiceMethodBeforeInteceptor();
        MethodBeforeAdviceInterceptor inteceptor = new MethodBeforeAdviceInterceptor(worldServiceMethodBeforeInteceptor);
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut("execution(* aop.bean.WorldService.explode(..))");
        MethodMatcher methodMatcher = aspectJExpressionPointcut.getMethodMatcher();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(inteceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
        //是否开启cglib 动态代理
        advisedSupport.setProxyTargetClass(false);
        TargetSource targetSource = new TargetSource(worldService);
        advisedSupport.setTargetSource(targetSource);
        ProxyFactory proxyFactory = new ProxyFactory(advisedSupport);
        WorldService proxy = (WorldService) proxyFactory.getProxy();
        proxy.explode();
    }
}
