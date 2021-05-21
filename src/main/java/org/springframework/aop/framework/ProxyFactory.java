package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * 代理工厂
 */
public class ProxyFactory {

    private final AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }

    public Object getProxy(){
        return createAopProxy().getProxy();
    }

    private AopProxy createAopProxy(){
        if(advisedSupport.isProxyTargetClass()){
            System.out.println("创建 CGLIB 动态代理");
            return new CglibAopProxy(advisedSupport);
        }
        System.out.println("创建 JDK 动态代理");
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
