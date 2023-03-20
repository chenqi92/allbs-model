package cn.allbs.constant;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * 类 DateConstant
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/17 13:20
 */
public interface DateConstant {

    /**
     * 标准日期时间正则，每个字段支持单个数字或2个数字，包括：
     * <pre>
     *     yyyy-MM-dd HH:mm:ss.SSSSSS
     *     yyyy-MM-dd HH:mm:ss.SSS
     *     yyyy-MM-dd HH:mm:ss
     *     yyyy-MM-dd HH:mm
     *     yyyy-MM-dd
     * </pre>
     *
     * @since 5.3.6
     */
    Pattern REGEX_NORM = Pattern.compile("\\d{4}-\\d{1,2}-\\d{1,2}(\\s\\d{1,2}:\\d{1,2}(:\\d{1,2})?)?(.\\d{1,6})?");

    //-------------------------------------------------------------------------------------------------------------------------------- Normal
    /**
     * 年格式：yyyy
     */
    String NORM_YEAR_PATTERN = "yyyy";
    /**
     * 年月格式：yyyy-MM
     */
    String NORM_MONTH_PATTERN = "yyyy-MM";
    /**
     * 年月格式 ：yyyy-MM
     */
    DateTimeFormatter NORM_MONTH_FORMATTER = createFormatter(NORM_MONTH_PATTERN);

    /**
     * 简单年月格式：yyyyMM
     */
    String SIMPLE_MONTH_PATTERN = "yyyyMM";
    /**
     * 简单年月格式 ：yyyyMM
     */
    DateTimeFormatter SIMPLE_MONTH_FORMATTER = createFormatter(SIMPLE_MONTH_PATTERN);

    /**
     * 标准日期格式：yyyy-MM-dd
     */
    String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 标准日期格式 ：yyyy-MM-dd
     */
    DateTimeFormatter NORM_DATE_FORMATTER = createFormatter(NORM_DATE_PATTERN);

    /**
     * 标准时间格式：HH:mm:ss
     */
    String NORM_TIME_PATTERN = "HH:mm:ss";
    /**
     * 标准日期格式 ：HH:mm:ss
     */
    DateTimeFormatter NORM_TIME_FORMATTER = createFormatter(NORM_TIME_PATTERN);

    /**
     * 标准日期时间格式，精确到分：yyyy-MM-dd HH:mm
     */
    String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    /**
     * 标准日期格式 ：yyyy-MM-dd HH:mm
     */
    DateTimeFormatter NORM_DATETIME_MINUTE_FORMATTER = createFormatter(NORM_DATETIME_MINUTE_PATTERN);

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 标准日期时间格式，精确到秒 ：yyyy-MM-dd HH:mm:ss
     */
    DateTimeFormatter NORM_DATETIME_FORMATTER = createFormatter(NORM_DATETIME_PATTERN);

    /**
     * 标准日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss.SSS
     */
    String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 标准日期时间格式，精确到毫秒 ：yyyy-MM-dd HH:mm:ss.SSS
     */
    DateTimeFormatter NORM_DATETIME_MS_FORMATTER = createFormatter(NORM_DATETIME_MS_PATTERN);

    /**
     * ISO8601日期时间格式，精确到毫秒：yyyy-MM-dd HH:mm:ss,SSS
     */
    String ISO8601_PATTERN = "yyyy-MM-dd HH:mm:ss,SSS";
    /**
     * 标准日期格式 ：yyyy-MM-dd HH:mm:ss,SSS
     */
    DateTimeFormatter ISO8601_FORMATTER = createFormatter(ISO8601_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日
     */
    String CHINESE_DATE_PATTERN = "yyyy年MM月dd日";
    /**
     * 标准日期格式 ：yyyy年MM月dd日
     */
    DateTimeFormatter CHINESE_DATE_FORMATTER = createFormatter(CHINESE_DATE_PATTERN);

    /**
     * 标准日期格式：yyyy年MM月dd日 HH时mm分ss秒
     */
    String CHINESE_DATE_TIME_PATTERN = "yyyy年MM月dd日HH时mm分ss秒";
    /**
     * 标准日期格式 ：yyyy年MM月dd日HH时mm分ss秒
     */
    DateTimeFormatter CHINESE_DATE_TIME_FORMATTER = createFormatter(CHINESE_DATE_TIME_PATTERN);

    //-------------------------------------------------------------------------------------------------------------------------------- Pure
    /**
     * 标准日期格式：yyyyMMdd
     */
    String PURE_DATE_PATTERN = "yyyyMMdd";
    /**
     * 标准日期格式 ：yyyyMMdd
     */
    DateTimeFormatter PURE_DATE_FORMATTER = createFormatter(PURE_DATE_PATTERN);

    /**
     * 标准日期格式：HHmmss
     */
    String PURE_TIME_PATTERN = "HHmmss";
    /**
     * 标准日期格式 ：HHmmss
     */
    DateTimeFormatter PURE_TIME_FORMATTER = createFormatter(PURE_TIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmss
     */
    String PURE_DATETIME_PATTERN = "yyyyMMddHHmmss";
    /**
     * 标准日期格式 ：yyyyMMddHHmmss
     */
    DateTimeFormatter PURE_DATETIME_FORMATTER = createFormatter(PURE_DATETIME_PATTERN);

    /**
     * 标准日期格式：yyyyMMddHHmmssSSS
     */
    String PURE_DATETIME_MS_PATTERN = "yyyyMMddHHmmssSSS";
    /**
     * 标准日期格式 ：yyyyMMddHHmmssSSS
     */
    DateTimeFormatter PURE_DATETIME_MS_FORMATTER = createFormatter(PURE_DATETIME_MS_PATTERN);

    //-------------------------------------------------------------------------------------------------------------------------------- Others
    /**
     * HTTP头中日期时间格式：EEE, dd MMM yyyy HH:mm:ss z
     */
    String HTTP_DATETIME_PATTERN = "EEE, dd MMM yyyy HH:mm:ss z";

    /**
     * JDK中日期时间格式：EEE MMM dd HH:mm:ss zzz yyyy
     */
    String JDK_DATETIME_PATTERN = "EEE MMM dd HH:mm:ss zzz yyyy";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss
     */
    String UTC_SIMPLE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss.SSS
     */
    String UTC_SIMPLE_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss'Z'
     */
    String UTC_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ssZ
     */
    String UTC_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssZ";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ssXXX
     */
    String UTC_WITH_XXX_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss.SSS'Z'
     */
    String UTC_MS_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ssZ
     */
    String UTC_MS_WITH_ZONE_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    /**
     * UTC时间：yyyy-MM-dd'T'HH:mm:ss.SSSXXX
     */
    String UTC_MS_WITH_XXX_OFFSET_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

    /**
     * 创建并为 {@link DateTimeFormatter} 赋予默认时区和位置信息，默认值为系统默认值。
     *
     * @param pattern 日期格式
     * @return {@link DateTimeFormatter}
     * @since 5.7.5
     */
    static DateTimeFormatter createFormatter(String pattern) {
        return DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
                .withZone(ZoneId.systemDefault());
    }
}
