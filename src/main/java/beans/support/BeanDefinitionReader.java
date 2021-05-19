package beans.support;

import beans.config.BeanDefinitionRegistry;
import beans.exception.BeansException;
import core.io.Resource;
import core.io.ResourceLoader;

/**
 *读取bean定义信息即BeanDefinition的接口
 */
public interface BeanDefinitionReader {

    /**
     * 获取BeanDefinition注册器
     * @return BeanDefinitionRegistry
     */
    BeanDefinitionRegistry getRegistry();

    /**
     * 获取资源加载器
     *
     * @return
     */
    ResourceLoader getResourceLoader();

    /**
     * 根据resource加载BeanDefinitions
     *
     * @param resource
     * @throws BeansException
     */
    void loadBeanDefinitons(Resource resource) throws BeansException;

    /**
     * 根据location 加载BeanDefinitions
     *
     * @param location
     * @throws BeansException
     */
    void loadBeanDefinitions(String location) throws  BeansException;

    /**
     * 根据location[] 加载BeanDefinitions
     *
     * @param locations
     * @throws BeansException
     */
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
