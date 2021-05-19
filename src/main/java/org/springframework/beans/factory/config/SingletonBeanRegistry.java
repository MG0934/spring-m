package org.springframework.beans.factory.config;

/**
 * 单利注册表
 */
public interface SingletonBeanRegistry {
    Object getSingleton(String name);
}
