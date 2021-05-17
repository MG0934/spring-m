package beans.factory;

import java.util.HashMap;
import java.util.Map;

public class BeanFactory {

    private final Map<String,Object> beanMap = new HashMap<String,Object>();

    public Object getBean(String beanName){
        return  beanMap.get(beanName);
    }

    public void registerBean(String beanName,Object beanObject){
        this.beanMap.put(beanName,beanObject);
    }
}
