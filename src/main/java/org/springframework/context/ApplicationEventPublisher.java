package org.springframework.context;

/**
 * 事件发布者得接口
 *
 */
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     *
     * @param event
     */
    void publishEvent(ApplicationEvent event);

}
