package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public interface ApplicationEventMulticaster {

    /**
     * 添加事件监听
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 移除事件监听
     *
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 添加广播事件
     *
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
