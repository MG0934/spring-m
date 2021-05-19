package bean;

public class Car {

    private String name;

    private Person person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void driver(){
        System.out.println("开着我亲爱的小汽车,车上有:"+person);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", person=" + person +
                '}';
    }
}
