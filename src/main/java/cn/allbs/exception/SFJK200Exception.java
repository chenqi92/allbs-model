package cn.allbs.exception;

/**
 * 类 GB293KException
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 10:40
 */
public class SFJK200Exception extends Exception {

    private Object result;

    public SFJK200Exception(String message) {
        super(message);
    }

    public SFJK200Exception(String message, Object result) {
        super(message);
        this.result = result;
    }

    public SFJK200Exception(String message, Throwable t) {
        super(message, t);
    }

    public static void separator_position(char c, Enum<?> mode) throws SFJK200Exception {
        throw new SFJK200Exception("Separator position is wrong: " + c + " cant in Mode: " + mode.name());
    }

    public static void static_data_match(Enum<?> flag, char[] tar, char[] src) throws SFJK200Exception {
        throw new SFJK200Exception("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void static_data_match(Enum<?> flag, byte[] tar, int src) throws SFJK200Exception {
        throw new SFJK200Exception("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + src);
    }

    public static void static_data_match(Enum<?> flag, byte[] tar, byte[] src) throws SFJK200Exception {
        throw new SFJK200Exception("Static data core: " + flag.toString() + ": " + new String(tar) + " -> " + new String(src));
    }

    public static void length_not_match(Enum<?> flag, int tar, int src) throws SFJK200Exception {
        throw new SFJK200Exception("Length does not core: " + flag.toString() + ": " + tar + " -> " + src);
    }

    public static void length_not_range(Enum<?> flag, int src, int min, int max) throws SFJK200Exception {
        throw new SFJK200Exception("Length does not in range: " + flag.toString() + ": " + src + " -> (" + min + "," + max + ")");
    }

    public static void field_is_missing(Enum<?> flag, String field) throws SFJK200Exception {
        throw new SFJK200Exception("Field is missing: " + flag.toString() + ": " + field);
    }

    public static void check_verification_failed(Enum<?> flag, char[] msg, char[] crc) throws SFJK200Exception {
        throw new SFJK200Exception("Arithmetic Verification failed: " + new String(msg) + ": " + new String(crc));
    }

    public static void check_verification_failed(Enum<?> flag, char[] msg, int crc) throws SFJK200Exception {
        throw new SFJK200Exception("Arithmetic Verification failed: " + new String(msg) + ": " + Integer.toHexString(crc));
    }

    public static void crc_verification_failed(Enum<?> flag, byte[] msg, int crc) throws SFJK200Exception {
        throw new SFJK200Exception("Crc Verification failed: " + new String(msg) + ": " + Integer.toHexString(crc));
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public SFJK200Exception withResult(Object result) {
        this.result = result;
        return this;
    }
}
