package lambda;


public class Person {

    private int age;

    private String name;

    public Person(String n, Integer a){
        this.name = n;
        this.age = a;
    }

    public Integer getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Person " + this.name +", age " + this.age;
    }
}
