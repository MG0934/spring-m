package aop;

import aop.bean.WorldService;
import aop.bean.WorldServiceImpl;
import aop.bean.WorldServiceInteceptor;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibAopProxy;

public class CglibDynamicProxyTest {
    @Test
    public  void test(){
        WorldService worldService = new WorldServiceImpl();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInteceptor methodInterceptor = new WorldServiceInteceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* aop.bean.WorldService.explode(..))").getMethodMatcher();

        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(methodInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);

        WorldService proxy = (WorldService) new CglibAopProxy(advisedSupport).getProxy();
        proxy.explode();
    }

}
