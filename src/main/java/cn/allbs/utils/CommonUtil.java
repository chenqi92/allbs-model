package cn.allbs.utils;

import com.google.common.base.Strings;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.*;

/**
 * 类 CommonUtil
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/20 11:54
 */
public class CommonUtil {

    @SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (isEmpty(values)) {
            return list(isLinked);
        } else {
            List<T> arrayList = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
            Collections.addAll((Collection) arrayList, values);
            return (List) arrayList;
        }
    }

    public static <T> boolean isEmpty(T[] array) {
        return array == null || array.length == 0;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    @SafeVarargs
    public static <T> HashSet<T> set(boolean isSorted, T... ts) {
        if (null == ts) {
            return (HashSet) (isSorted ? new LinkedHashSet() : new HashSet());
        } else {
            int initialCapacity = Math.max((int) ((float) ts.length / 0.75F) + 1, 16);
            HashSet<T> set = isSorted ? new LinkedHashSet(initialCapacity) : new HashSet(initialCapacity);
            Collections.addAll((Collection) set, ts);
            return (HashSet) set;
        }
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... ts) {
        return set(false, ts);
    }

    public static String concat(boolean isNullToEmpty, CharSequence... strs) {
        StringBuilder sb = new StringBuilder();
        CharSequence[] var3 = strs;
        int var4 = strs.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            CharSequence str = var3[var5];
            sb.append((CharSequence) (isNullToEmpty ? nullToEmpty(str) : str));
        }

        return sb.toString();
    }

    public static String nullToEmpty(CharSequence str) {
        return nullToDefault(str, "");
    }

    public static String nullToDefault(CharSequence str, String defaultStr) {
        return str == null ? defaultStr : str.toString();
    }

    public static <T> T[] toArray(Collection<T> collection, Class<T> componentType) {
        return collection.toArray(newArray(componentType, 0));
    }

    public static <T> T[] newArray(Class<?> componentType, int newSize) {
        return (T[]) Array.newInstance(componentType, newSize);
    }

    public static Object[] newArray(int newSize) {
        return new Object[newSize];
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return array != null && array.length != 0;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    public static boolean isNotNull(Object obj) {
        return null != obj;
    }

    public static boolean isValidIfNumber(Object obj) {
        return !(obj instanceof Number) || isValidNumber((Number) obj);
    }

    public static boolean isValidNumber(Number number) {
        if (number instanceof Double) {
            return !((Double) number).isInfinite() && !((Double) number).isNaN();
        } else if (!(number instanceof Float)) {
            return true;
        } else {
            return !((Float) number).isInfinite() && !((Float) number).isNaN();
        }
    }

    public static boolean hasEmpty(Double... args) {
        if (isNotEmpty(args)) {
            Object[] var1 = args;
            int var2 = args.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                Object element = var1[var3];
                if (element == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isAllNotEmpty(Double... args) {
        return !hasEmpty(args);
    }

    public static BigDecimal round(double v, int scale) {
        return round(v, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(double v, int scale, RoundingMode roundingMode) {
        return round(Double.toString(v), scale, roundingMode);
    }

    public static BigDecimal round(String numberStr, int scale, RoundingMode roundingMode) {
        if (scale < 0) {
            scale = 0;
        }

        return round(toBigDecimal(numberStr), scale, roundingMode);
    }

    public static BigDecimal toBigDecimal(String number) {
        try {
            number = parseNumber(number).toString();
        } catch (Exception var2) {
        }

        return Strings.isNullOrEmpty(number) ? BigDecimal.ZERO : new BigDecimal(number);
    }

    public static Number parseNumber(String numberStr) throws NumberFormatException {
        try {
            return NumberFormat.getInstance().parse(numberStr);
        } catch (ParseException var3) {
            NumberFormatException nfe = new NumberFormatException(var3.getMessage());
            nfe.initCause(var3);
            throw nfe;
        }
    }

    public static BigDecimal round(BigDecimal number, int scale) {
        return round(number, scale, RoundingMode.HALF_UP);
    }

    public static BigDecimal round(BigDecimal number, int scale, RoundingMode roundingMode) {
        if (null == number) {
            number = BigDecimal.ZERO;
        }

        if (scale < 0) {
            scale = 0;
        }

        if (null == roundingMode) {
            roundingMode = RoundingMode.HALF_UP;
        }

        return number.setScale(scale, roundingMode);
    }
}
