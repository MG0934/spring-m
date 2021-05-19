package org.springframework.context.support;

public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext{

    private String[] configLocations;

    public ClassPathXmlApplicationContext(String configLocation){
        this(new String[]{configLocation});
    }

    public ClassPathXmlApplicationContext(String[] configLocations) {
        this.configLocations = configLocations;
        //从xml文件加载BeanDefinition，并且自动刷新上下文
        refresh();
    }

    @Override
    protected String[] getConfigLocation() {
        return configLocations;
    }
}
