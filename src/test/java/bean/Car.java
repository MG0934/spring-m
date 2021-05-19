package bean;

public class Car {

    private Person person;

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
                "person=" + person +
                '}';
    }
}
