package cn.allbs.utils.gb26875.utils;

import cn.allbs.exception.GB26875Exception;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * @author ChenQi
 */
@UtilityClass
public class VerifyUtil {

    public static void verifyChar(char[] tar, char[] src, Enum<?> e) throws GB26875Exception {
        Objects.requireNonNull(tar);
        Objects.requireNonNull(src);
        if (!Arrays.equals(src, tar)) {
            GB26875Exception.static_data_match(e, tar, src);
        }
    }

    public static void verifyChar(byte[] tar, char[] src, Enum<?> e) throws GB26875Exception {
        char[] c = new String(tar).toCharArray();
        verifyChar(src, c, e);
    }

    public static void verifyLen(int count, int length, Enum<?> e) throws GB26875Exception {
        if (count != length) {
            GB26875Exception.length_not_match(e, count, length);
        }
    }

    public static void verifyLen(String str, int min, int max, Enum<?> e) throws GB26875Exception {
        if (str == null) {
            return;
        }
        int len = str.length();

        if (len >= min && len <= max) {
        } else {
            GB26875Exception.length_not_range(e, len, min, max);
        }
    }

    public static void verifyLen(String str, int length, Enum<?> e) throws GB26875Exception {
        if (str == null) {
            return;
        }

        verifyLen(str.length(), length, e);
    }

    public static void verifyRange(int src, int min, int max, Enum<?> e) throws GB26875Exception {
        if (src >= min && src <= max) {
        } else {
            GB26875Exception.length_not_range(e, src, min, max);
        }
    }

    public static String verifyRange(String str, int min, int max, Enum<?> e) throws GB26875Exception {
        int src = 0;
        if (str != null) {
            src = str.length();
        }

        if (src >= min && src <= max) {
        } else {
            GB26875Exception.length_not_range(e, src, min, max);
        }
        return str;
    }

    public static void verifyHave(Map<String, ?> object, Enum<?> e) throws GB26875Exception {
        if (!object.containsKey(e.name())) {
            GB26875Exception.field_is_missing(e, e.name());
        }
    }

    public static void verifyCheck(boolean check) throws GB26875Exception {
        if (check) {
            throw new GB26875Exception("报文数据校验不通过!");
        }
    }
}
