package lambda;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface Comparator<T> {

    static <T, U extends Comparable<U>> Comparator<T> comparing(Function<T, U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);

        return (p1, p2) -> {
            U s1 = keyExtractor.apply(p1);
            U s2 = keyExtractor.apply(p2);
            return s1.compareTo(s2);
        };
    }

    int compare(T o1, T o2);

    default <U extends Comparable<U>> Comparator<T> thenComparing(Function<T, U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        Comparator<T> other = comparing(keyExtractor);
        return this.thenComparing(other);
    }

    default Comparator<T> thenComparing(Comparator<T> other) {
        Objects.requireNonNull(other);
        return (T o1, T o2) -> {

            int compare = this.compare(o1, o2);
            if (compare == 0) {
                return other.compare(o1, o2);
            }
            return compare;
        };
    }

}
