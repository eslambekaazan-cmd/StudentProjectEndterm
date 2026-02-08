package utils;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SortingUtils {

    public static <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
        return list;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        return list.stream().filter(predicate).collect(Collectors.toList());
    }
}
