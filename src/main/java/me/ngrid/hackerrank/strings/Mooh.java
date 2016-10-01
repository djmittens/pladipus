package me.ngrid.hackerrank.strings;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 *
 */
public class Mooh {
    private static List<Supplier<Integer>> flyAKite() {
        List<Supplier<Integer>> out = new ArrayList<>();
        for(int i = 0; i < 3; i ++) {
            final Integer j = i;
            out.add(() -> j);
        }
        return out;
    }
    public static void main(String[] args) {
        flyAKite().forEach(i -> System.out.println(i.get()));
    }
}
