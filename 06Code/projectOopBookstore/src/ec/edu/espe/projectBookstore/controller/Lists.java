package ec.edu.espe.projectBookstore.controller;

import java.util.List;
import java.util.function.ToIntFunction;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public final class Lists {

    private Lists() {
    }

    public static <T> T find(List<T> list, int id, ToIntFunction<T> idOf) {
        for (T item : list) {
            if (idOf.applyAsInt(item) == id) {
                return item;
            }
        }
        return null;
    }

    public static <T> int nextId(List<T> list, ToIntFunction<T> idOf) {
        int max = 0;
        for (T item : list) {
            max = Math.max(max, idOf.applyAsInt(item));
        }
        return max + 1;
    }
}
