package lambda;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayWithStreams {

    public static void main(String[] args) {

        Person deb = new Person("deb", 32);
        Person sola = new Person("sola", 32);
        Person baba = new Person("baba", 72);
        Person ma = new Person("ma", 65);
        Person nullObj = null;

        Map<String, Integer> collect = Stream.of(deb, baba, sola, ma,nullObj)
                .filter(Objects::nonNull)
                .filter(p -> p.getAge() > 60)
                .collect(Collectors.toMap(Person::getName, Person::getAge));

        System.out.println(collect);


    }
}
