package beans.config;

/**
 * 单利注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
