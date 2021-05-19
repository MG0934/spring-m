package beans.xml;

import beans.config.BeanDefinition;
import beans.config.BeanDefinitionRegistry;
import beans.config.BeanReferece;
import beans.exception.BeansException;
import beans.support.AbstractBeanDefinitionReader;
import beans.utils.PropertyValue;
import beans.utils.PropertyValues;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import core.io.Resource;
import core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader{

    public static final String BEAN_ELEMENT = "bean";

    public static final String PROPERTY_ELEMENT = "property";

    public static final String ID_ATTRIBUTE = "id";

    public static final String NAME_ATTRIBUTE = "name";

    public static final String CLASS_ATTRIBUTE = "class";

    public static final String VALUE_ATTRIBUTE = "value";

    public static final String REF_ATTRIBUTE = "ref";

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitons(resource);
    }


    @Override
    public void loadBeanDefinitons(Resource resource) throws BeansException {

        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            }finally {
                inputStream.close();
            }
        }catch (IOException ex){
            throw new BeansException("IOException parsing XML document from "+resource,ex);
        }
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            if(childNodes.item(i) instanceof Element){
                if(BEAN_ELEMENT.equals(((Element)childNodes.item(i)).getNodeName())){
                    //解析bean标签
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    //获取class文件
                    Class<?> clazz =null;
                    try {
                       clazz = Class.forName(className);
                    }catch (ClassNotFoundException e){
                        throw  new BeansException("cannot find class :" + className);
                    }

                    //id 优先于 name
                    String beanName = StrUtil.isNotEmpty(id)?id:name;
                    if(StrUtil.isEmpty(beanName)){
                        //如果id和name为空则将类名的第一个字母小写作为bean的名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    //创建BeanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition(clazz,new PropertyValues());
                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if(bean.getChildNodes().item(j) instanceof Element){
                            if (PROPERTY_ELEMENT.equals(((Element) bean.getChildNodes().item(j)).getNodeName())) {
                                Element property = (Element) bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                                if(StrUtil.isEmpty(nameAttribute)){
                                    throw new BeansException("The name attribute cannot be null or empty");
                                }

                                Object value = valueAttribute;
                                if (StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReferece(refAttribute);
                                }

                                //封装参数
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }

                    if(getRegistry().containsBeanDefinition(beanName)){
                        //beanName不能重名
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }

                    //注册BeanDefinition
                    getRegistry().registryBeanDefinition(beanName,beanDefinition);
                }
            }
        }
    }
}
