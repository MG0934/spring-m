package org.springframework.aop.aspectj;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;

public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor{

    private AspectJExpressionPointcut pointcut;

    private Advice advice;

    private String expression;

    public void setExpression(String expression){
        this.expression = expression;
    }


    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {

        if(pointcut!=null){
            return pointcut;
        }

        pointcut = new AspectJExpressionPointcut(expression);

        return  pointcut;
    }

    public void setAdvice(Advice advice){
        this.advice = advice;
    }
}
