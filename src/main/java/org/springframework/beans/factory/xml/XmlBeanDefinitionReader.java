package org.springframework.beans.factory.xml;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.config.BeanReferece;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
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

    public static final String INIT_METHOD_ATTRIBUTE = "init-method";

    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

    public static  final String SCOPE_ATTRIBUTE = "scope";

    public static  final String BASE_PACKAGE_ATTRIBUTE = "base-package";

    //component-scan
    public static  final String COMPONENT_PACKAGE_ATTRIBUTE = "context:component-scan";

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
                //??????context:component-scan???????????????????????????????????????????????????????????????BeanDefinition
                String nodeName = childNodes.item(i).getNodeName();
                if(COMPONENT_PACKAGE_ATTRIBUTE.equals(((Element)childNodes.item(i)).getNodeName())){
                    Element component = (Element) childNodes.item(i);
                    String scanPath = component.getAttribute(BASE_PACKAGE_ATTRIBUTE);
                    if(StrUtil.isEmpty(scanPath)){
                        throw new BeansException("The value of base-package attribute can not be empty or null");
                    }
                    scanPackage(scanPath);
                }
                if(BEAN_ELEMENT.equals(((Element)childNodes.item(i)).getNodeName())){
                    //??????bean??????
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    String destroyMethodName=bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);
                    String beanScope = bean.getAttribute(SCOPE_ATTRIBUTE);

                    //??????class??????
                    Class<?> clazz =null;
                    try {
                       clazz = Class.forName(className);
                    }catch (ClassNotFoundException e){
                        throw  new BeansException("cannot find class :" + className);
                    }

                    //id ????????? name
                    String beanName = StrUtil.isNotEmpty(id)?id:name;
                    if(StrUtil.isEmpty(beanName)){
                        //??????id???name????????????????????????????????????????????????bean?????????
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    //??????BeanDefinition
                    BeanDefinition beanDefinition = new BeanDefinition(clazz,new PropertyValues());
                    //??????initMethodName ??? destroyMethodName
                    beanDefinition.setInitMethodName(initMethodName);
                    beanDefinition.setDestoryMethodName(destroyMethodName);
                    if(StrUtil.isNotEmpty(beanScope)){
                        beanDefinition.setScope(beanScope);
                    }


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

                                //????????????
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }

                    if(getRegistry().containsBeanDefinition(beanName)){
                        //beanName????????????
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }

                    //??????BeanDefinition
                    getRegistry().registryBeanDefinition(beanName,beanDefinition);
                }
            }
        }
    }

    /**
     * ????????????Component?????????????????????????????????BeanDefinition
     *
     * @param scanPath
     */
    private void scanPackage(String scanPath) {
        String[] basePackages = StrUtil.splitToArray(scanPath, ',');
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(getRegistry());
        scanner.doScan(basePackages);
    }
}