package org.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 *
 * pointcut 和 advice得组合
 *
 */
public interface Advisor {

    Advice getAdvice();

}
