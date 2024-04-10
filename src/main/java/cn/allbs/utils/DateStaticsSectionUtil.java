package cn.allbs.utils;

import cn.allbs.constant.ParamConstant;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 日期区间map构建
 * <p>
 * 构建为一个横轴表为日期，纵坐标为默认数量 0 的map供后期塞值
 *
 * @author ChenQi
 */
@UtilityClass
public class DateStaticsSectionUtil {

    /**
     * 分钟构建Map
     * <p>
     * 传入开始时间 结束时间 构建一个Map
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  间隔分钟数
     * @param pattern   格式化
     * @return Map
     */
    public <T> Map<String, T> minuteSection(LocalDateTime startTime, LocalDateTime endTime, int interval, String pattern, T defaultValue) {
        Duration duration = Duration.between(startTime, endTime);
        int num = BigDecimal.valueOf(Math.ceil(duration.toMinutes() * (float) 4 / 3 + 1)).intValue();
        Map<String, T> result = new LinkedHashMap<>(num);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), defaultValue);
            startTime = startTime.plusMinutes(interval);
        }
        return result;
    }

    /**
     * 分钟构建Map
     * <p>
     * 传入开始时间 结束时间 构建一个Map
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param interval  间隔分钟数
     * @return Map
     */
    public <T> Map<String, T> minuteSection(LocalDateTime startTime, LocalDateTime endTime, int interval, T defaultValue) {
        return minuteSection(startTime, endTime, interval, "MM-dd HH:mm", defaultValue);
    }

    /**
     * 分钟构建Map
     * <p>
     * 传入开始时间 结束时间 构建一个Map 默认间隔一分钟
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pattern   格式化
     * @return Map
     */
    public <T> Map<String, T> minuteSection(LocalDateTime startTime, LocalDateTime endTime, String pattern, T defaultValue) {
        return minuteSection(startTime, endTime, 1, pattern, defaultValue);
    }

    /**
     * 分钟构建Map
     * <p>
     * 传入开始时间 结束时间 构建一个Map 默认间隔一分钟
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return Map
     */
    public <T> Map<String, T> minuteSection(LocalDateTime startTime, LocalDateTime endTime, T defaultValue) {
        return minuteSection(startTime, endTime, 1, "MM-dd HH:mm", defaultValue);
    }

    /**
     * 开始结束时间构造月Map
     * <p>
     * 构建以月为跨度，该月份中所有日为key  value为0的map
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @param pattern   格式化
     * @return map
     */
    public <T> Map<String, T> daySection(LocalDate startTime, LocalDate endTime, String pattern, T defaultValue) {
        Period betweenDays = Period.between(startTime, endTime);
        int num = BigDecimal.valueOf(Math.ceil(betweenDays.getDays() * (float) 4 / 3 + 1)).intValue();
        Map<String, T> result = new LinkedHashMap<>(num);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), defaultValue);
            startTime = startTime.plusDays(1);
        }
        return result;
    }

    /**
     * 开始结束时间构造月Map
     * <p>
     * 构建以月为跨度，该月份中所有日为key  value为0的map
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return map
     */
    public <T> Map<String, T> daySection(LocalDate startTime, LocalDate endTime, T defaultValue) {
        return daySection(startTime, endTime, "MM-dd", defaultValue);
    }

    /**
     * 天 24小时Map
     * <p>
     * 根据某一天构建固定间隔小时数的Map
     *
     * @param date     某天日期
     * @param interval 间隔小时数
     * @param pattern  格式化
     * @return Map
     */
    public <T> Map<String, T> dayHour(LocalDate date, int interval, String pattern, T defaultValue) {
        LocalDateTime startTime = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
        Map<String, T> result = new LinkedHashMap<>(33);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), defaultValue);
            startTime = startTime.plusHours(interval);
        }
        return result;
    }

    /**
     * 天 24小时Map
     * <p>
     * 根据某一天构建固定间隔小时数的Map 默认一小时
     *
     * @param date    某天日期
     * @param pattern 格式化
     * @return Map
     */
    public <T> Map<String, T> dayHour(LocalDate date, String pattern, T defaultValue) {
        return dayHour(date, 1, pattern, defaultValue);
    }

    /**
     * 天 24小时Map
     * <p>
     * 根据某一天构建固定间隔小时数的Map 默认一小时
     *
     * @param date     某天日期
     * @param interval 间隔小时数
     * @return Map
     */
    public <T> Map<String, T> dayHour(LocalDate date, int interval, T defaultValue) {
        return dayHour(date, interval, "HH", defaultValue);
    }

    /**
     * 天 24小时Map
     * <p>
     * 根据某一天构建固定间隔小时数的Map 默认一小时
     *
     * @param date 某天日期
     * @return Map
     */
    public <T> Map<String, T> dayHour(LocalDate date, T defaultValue) {
        return dayHour(date, 1, "HH", defaultValue);
    }

    /**
     * 根据年月构建Map
     * <p>
     * 传入年、月构建该年月内所有天的Map
     *
     * @param year    年
     * @param month   月
     * @param pattern 格式化
     * @return Map
     */
    public <T> Map<String, T> monthDay(int year, int month, String pattern, T defaultValue) {
        Map<String, T> result = new LinkedHashMap<>(41);
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1);
        while (startDate.isBefore(endDate)) {
            result.put(startDate.format(DateTimeFormatter.ofPattern(pattern)), defaultValue);
            startDate = startDate.plusDays(1);
        }
        return result;
    }

    /**
     * 根据年月构建Map
     * <p>
     * 传入年、月构建该年月内所有天的Map
     *
     * @param year  年
     * @param month 月
     * @return Map
     */
    public <T> Map<String, T> monthDay(int year, int month, T defaultValue) {
        return monthDay(year, month, "MM-dd", defaultValue);
    }

    /**
     * 根据年份构造Map
     * <p>
     * 传入年份,构建该年所有月份的Map
     *
     * @param year    年
     * @param pattern 格式化
     * @return Map
     */
    public <T> Map<String, T> yearMonth(int year, String pattern, T defaultValue) {
        Map<String, T> result = new LinkedHashMap<>(17);
        for (int i = 1; i <= ParamConstant.MONTH_COUNT; i++) {
            LocalDate date = LocalDate.of(year, i, 1);
            result.put(date.format(DateTimeFormatter.ofPattern(pattern)), defaultValue);
        }
        return result;
    }

    /**
     * 根据年份构造Map
     * <p>
     * 传入年份,构建该年所有月份的Map
     *
     * @param year 年
     * @return Map
     */
    public <T> Map<String, T> yearMonth(int year, T defaultValue) {
        return yearMonth(year, "MM", defaultValue);
    }
}
