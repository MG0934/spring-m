package org.springframework.context.annotation;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.cglib.BeanCopierCache;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Set;

public class ClassPathBeanDefinitionScanner extends ClassPathScanningCandidateComponentProvider{

    public static final String AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME = "org.springframework.context.annotation.internalAutowiredAnnotationProcessor";

    private BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void doScan(String... basePackages){
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidateComponents = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidateComponents) {
                //解析Bean的作用域
                String beanScope = resolveBeanScope(candidate);
                if(StrUtil.isNotEmpty(beanScope)){
                    candidate.setScope(beanScope);
                }
                //生成bean的名称
                String beanName = determineBeanName(candidate);
                //注册BeanDefinition
                registry.registryBeanDefinition(beanName,candidate);
            }
        }

        //注册处理@Autowired和@Value注解的BeanPostProcessor
        registry.registryBeanDefinition(AUTOWIRED_ANNOTATION_PROCESSOR_BEAN_NAME, new BeanDefinition(AutowriteAnnotationBeanPostProcessor.class));
    }

    /**
     * 用于生成bean的名称
     * @param candidate
     * @return
     */
    private String determineBeanName(BeanDefinition candidate) {
        Class beanClass = candidate.getBeanClass();
        Component component = (Component) beanClass.getAnnotation(Component.class);
        String value = component.value();
        if(StrUtil.isEmpty(value)){
            value = StrUtil.lowerFirst(beanClass.getSimpleName());
        }

        return value;
    }

    /**
     * 用于解析bean的scope范围
     * @param candidate
     * @return
     */
    private String resolveBeanScope(BeanDefinition candidate) {
        Class<? extends BeanDefinition> beanClass = candidate.getClass();
        Scope scope = beanClass.getAnnotation(Scope.class);
        if(scope!=null){
            return scope.value();
        }
        return StrUtil.EMPTY;
    }
}
