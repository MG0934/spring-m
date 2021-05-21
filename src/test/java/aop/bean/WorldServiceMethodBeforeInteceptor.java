package aop.bean;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class WorldServiceMethodBeforeInteceptor implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("执行 method before inteceptor");
    }
}
