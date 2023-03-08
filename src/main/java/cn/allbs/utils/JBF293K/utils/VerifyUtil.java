package cn.allbs.utils.JBF293K.utils;

import cn.allbs.exception.JBF293KException;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * 类 VerifyUtil
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 14:18
 */
@UtilityClass
public class VerifyUtil {

    public static void verifyChar(char[] tar, char[] src, Enum<?> e) throws JBF293KException {
        Objects.requireNonNull(tar);
        Objects.requireNonNull(src);
        if (!Arrays.equals(src, tar)) {
            JBF293KException.static_data_match(e, tar, src);
        }
    }

    public static void verifyByte(byte[] tar, byte[] src, Enum<?> e) throws JBF293KException {
        Objects.requireNonNull(tar);
        Objects.requireNonNull(src);
        if (!Arrays.equals(src, tar)) {
            JBF293KException.static_data_match(e, tar, src);
        }
    }

    public static void verifyByte(byte[] tar, int src, Enum<?> e) throws JBF293KException {
        Objects.requireNonNull(tar);
        if ((tar[0] & 0xff) != src) {
            JBF293KException.static_data_match(e, tar, src);
        }
    }

    public static void verifyChar(byte[] tar, char[] src, Enum<?> e) throws JBF293KException {
        char[] c = new String(tar).toCharArray();
        verifyChar(src, c, e);
    }

    public static void verifyLen(int count, int length, Enum<?> e) throws JBF293KException {
        if (count != length) {
            JBF293KException.length_not_match(e, count, length);
        }
    }

    public static void verifyLen(String str, int min, int max, Enum<?> e) throws JBF293KException {
        if (str == null) {
            return;
        }
        int len = str.length();

        if (len >= min && len <= max) {
        } else {
            JBF293KException.length_not_range(e, len, min, max);
        }
    }

    public static void verifyLen(String str, int length, Enum<?> e) throws JBF293KException {
        if (str == null) {
            return;
        }

        verifyLen(str.length(), length, e);
    }

    public static void verifyRange(int src, int min, int max, Enum<?> e) throws JBF293KException {
        if (src >= min && src <= max) {
        } else {
            JBF293KException.length_not_range(e, src, min, max);
        }
    }

    public static String verifyRange(String str, int min, int max, Enum<?> e) throws JBF293KException {
        int src = 0;
        if (str != null) {
            src = str.length();
        }

        if (src >= min && src <= max) {
        } else {
            JBF293KException.length_not_range(e, src, min, max);
        }
        return str;
    }

    public static void verifyHave(Map<String, ?> object, Enum<?> e) throws JBF293KException {
        if (!object.containsKey(e.name())) {
            JBF293KException.field_is_missing(e, e.name());
        }
    }

    public static void verifyCheck(boolean check) throws JBF293KException {
        if (check) {
            throw new JBF293KException("报文数据校验不通过!");
        }
    }
}
