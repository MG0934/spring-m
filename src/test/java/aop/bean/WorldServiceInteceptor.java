package aop.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class WorldServiceInteceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("动态代理之前");
        Object proceed = invocation.proceed();
        System.out.println("动态代理之后");
        return proceed;
    }
}
