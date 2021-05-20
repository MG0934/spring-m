package org.springframework.context;

import java.util.EventListener;

/**
 * 事件得监听
 *
 * @param <E>
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);

}
