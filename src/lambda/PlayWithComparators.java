package lambda;

import java.util.function.Function;

public class PlayWithComparators {

    public static void main(String[] args) {

        Person deb = new Person("deb", 32);
        Person sola = new Person("sola", 32);
        Person baba = new Person("baba", 72);
        Person ma = new Person("ma", 65);

        Function<Person, Integer> getAge = Person::getAge;
        Function<Person, String> getName = Person::getName;

        Comparator<Person> personComparator = Comparator.comparing(Person::getName)
                                                        .thenComparing(Person::getAge);

        System.out.println("baba > deb " + (personComparator.compare(baba,deb) > 0));
        System.out.println("deb > sola " + (personComparator.compare(deb,sola) < 0));
    }
}
