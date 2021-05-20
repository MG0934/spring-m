package bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Person implements InitializingBean, DisposableBean {
    String name;

    int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void initMethod(){
        System.out.println("init method");
    }

    public void destoryMethos(){
        System.out.println("destroy method");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("disposableBean destroy method");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("initializingBean afterPropertiesSet method");
    }
}
