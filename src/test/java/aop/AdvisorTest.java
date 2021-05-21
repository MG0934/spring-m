package aop;

import aop.bean.WorldService;
import aop.bean.WorldServiceImpl;
import aop.bean.WorldServiceMethodBeforeInteceptor;
import bean.Person;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.TargetSource;
import org.springframework.aop.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class AdvisorTest {

    @Test
    public void testPointcutExpression() throws NoSuchMethodException {

        WorldService worldService = new WorldServiceImpl();


        String expression = "execution(* aop.bean.WorldService.explode(..))";
        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression(expression);

        MethodBeforeAdviceInterceptor methodInteceptor = new MethodBeforeAdviceInterceptor(new WorldServiceMethodBeforeInteceptor());
        advisor.setAdvice(methodInteceptor);

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();

        if(classFilter.matches(worldService.getClass())){
            //创建 advisedSupport
            AdvisedSupport advisedSupport = new AdvisedSupport();
            TargetSource targetSource = new TargetSource(worldService);
            advisedSupport.setProxyTargetClass(false);
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodMatcher(advisor.getPointcut().getMethodMatcher());
            advisedSupport.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());

            WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();

            proxy.explode();
        }
    }
}
