package ec.edu.espe.projectBookstore.view;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Mateo Dominguez, Brain Training, @ESPE
 */
public final class TextUtil {

    private TextUtil() {
    }

    public static void requireText(String value, String label) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(label + " cannot be empty");
        }
    }

    public static String clean(String value) {
        return value == null ? "" : value.trim();
    }

    public static String formatDate(Date date) {
        return date == null ? "-" : new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }
}
