package beans.factory;

import beans.exception.BeansException;

import java.util.Map;

/**
 *
 * bean 返回集合
 *
 */
public interface ListableBeanFactory extends BeanFactory{


    /**
     * 返回定义的所有bean的名称
     * @return 集合
     */
    String[] getBeanDefinitionNames();

    /**
     * 返回指定类型的所有实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String,T> getBeansOfType(Class<T> type) throws BeansException;

}
