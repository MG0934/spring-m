package bean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Object getObject() throws Exception {
        Car car = new Car();
        car.setName("moooxy");
        Person person = new Person();
        person.setName(this.name);
        person.setAge(19);
        car.setPerson(person);
        return car;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
