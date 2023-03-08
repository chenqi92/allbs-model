package cn.allbs.exception;

/**
 * 消防检测协议转换异常
 *
 * @author ChenQi
 */
public class GB26875Exception extends Exception {

    private Object result;

    public GB26875Exception(String message) {
        super(message);
    }

    public GB26875Exception(String message, Object result) {
        super(message);
        this.result = result;
    }

    public GB26875Exception(String message, Throwable t) {
        super(message, t);
    }

    public static void separator_position(char c, Enum<?> mode) throws GB26875Exception {
        throw new GB26875Exception("Separator position is wrong: " + c + " cant in Mode: " + mode.name());
    }

    public static void static_data_match(Enum<?> flag, char[] tar, char[] src) throws GB26875Exception {
        throw new GB26875Exception("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void length_not_match(Enum<?> flag, int tar, int src) throws GB26875Exception {
        throw new GB26875Exception("Length does not core: " + flag.toString() + ": " + tar + " -> " + src);
    }

    public static void length_not_range(Enum<?> flag, int src, int min, int max) throws GB26875Exception {
        throw new GB26875Exception("Length does not in range: " + flag.toString() + ": " + src + " -> (" + min + "," + max + ")");
    }

    public static void field_is_missing(Enum<?> flag, String field) throws GB26875Exception {
        throw new GB26875Exception("Field is missing: " + flag.toString() + ": " + field);
    }

    public static void check_verification_failed(Enum<?> flag, char[] msg, char[] crc) throws GB26875Exception {
        throw new GB26875Exception("Arithmetic Verification failed: " + new String(msg) + ": " + new String(crc));
    }

    public static void check_verification_failed(Enum<?> flag, char[] msg, int crc) throws GB26875Exception {
        throw new GB26875Exception("Arithmetic Verification failed: " + new String(msg) + ": " + Integer.toHexString(crc));
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public GB26875Exception withResult(Object result) {
        this.result = result;
        return this;
    }
}
