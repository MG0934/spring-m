package org.springframework.beans.factory.config;

public class BeanReferece {

    private String beanName;

    public BeanReferece() {

    }

    public BeanReferece(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
