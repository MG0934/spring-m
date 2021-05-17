package beans.support;

import beans.config.BeanDefinition;

public abstract class AbstrctAutowriteCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 默认初始化 简单实例化策略
     */
    InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object createBean(BeanDefinition bd) {
        return doCreateBean(bd);
    }

    protected Object doCreateBean(BeanDefinition bd) {
        return getInstantiationStrategy().instantiate(bd);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    /**
     * 传入 自定义的 实例化策略
     * @param instantiationStrategy
     */
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
