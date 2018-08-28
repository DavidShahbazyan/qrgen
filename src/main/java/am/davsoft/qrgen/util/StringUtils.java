package am.davsoft.qrgen.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author David.Shahbazyan
 * @since Oct 12, 2016
 */
public final class StringUtils {
    public static final ToStringVisitor TO_STRING_VISITOR = new ToStringVisitor();
    private static final String DEFAULT_DELIMITER = " ";
    private StringUtils() { /* NO INSTANTIATION */ }

    public static String objConcat(List<Object> list) {
        return objConcat(list, DEFAULT_DELIMITER);
    }
    public static String objConcat(List<Object> list, String delimiter) {
        return objConcat(list, delimiter, o -> o.toString());
    }
    public static String objConcat(List<Object> list, String delimiter, Function<Object, String> itemAsString) {
        List<String> sList = new ArrayList<>(list.size());
        list.forEach(o -> sList.add(itemAsString.apply(o)));
        return strConcat(sList, delimiter);
    }

    public static String strConcat(List<String> list) {
        return strConcat(list, DEFAULT_DELIMITER);
    }
    public static String strConcat(String... list) {
        return strConcat(Arrays.asList(list), DEFAULT_DELIMITER);
    }
    public static String strConcat(List<String> list, String delimiter) {
        return list.stream().collect(Collectors.joining(delimiter));
    }

    public static final class ToStringVisitor {
        private ToStringVisitor() { /* NO INSTANTIATION */ }
        // Short calls
        public String getOrDefault(String string) {
            return getOrDefault(string, "");
        }
        public String getOrDefault(Integer integer) {
            return getOrDefault(integer, "");
        }
        public String getOrDefault(BigDecimal bigDecimal) {
            return getOrDefault(bigDecimal, "");
        }

        // Full calls
        public String getOrDefault(String string, String defaultValue) {
            return string != null ? string : defaultValue;
        }

        public String getOrDefault(Integer integer, String defaultValue) {
            return integer != null ? String.valueOf(integer) : defaultValue;
        }

        public String getOrDefault(BigDecimal bigDecimal, String defaultValue) {
            return bigDecimal != null ? String.valueOf(bigDecimal) : defaultValue;
        }

    }
}
