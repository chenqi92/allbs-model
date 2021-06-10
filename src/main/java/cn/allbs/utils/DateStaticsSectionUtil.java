package cn.allbs.utils;

import cn.allbs.constant.ParamConstant;
import cn.hutool.core.convert.Convert;
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
    public Map<String, BigDecimal> minuteSection(LocalDateTime startTime, LocalDateTime endTime, int interval, String pattern) {
        Duration duration = Duration.between(startTime, endTime);
        int num = Convert.toInt(Math.ceil(duration.toMinutes() * (float) 4 / 3 + 1));
        Map<String, BigDecimal> result = new LinkedHashMap<>(num);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), new BigDecimal(0));
            startTime = startTime.plus(interval, ChronoUnit.MINUTES);
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
    public Map<String, BigDecimal> minuteSection(LocalDateTime startTime, LocalDateTime endTime, int interval) {
        return minuteSection(startTime, endTime, interval, "MM-dd HH:mm");
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
    public Map<String, BigDecimal> minuteSection(LocalDateTime startTime, LocalDateTime endTime, String pattern) {
        return minuteSection(startTime, endTime, 1, pattern);
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
    public Map<String, BigDecimal> minuteSection(LocalDateTime startTime, LocalDateTime endTime) {
        return minuteSection(startTime, endTime, 1, "MM-dd HH:mm");
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
    public Map<String, BigDecimal> daySection(LocalDate startTime, LocalDate endTime, String pattern) {
        Period betweenDays = Period.between(startTime, endTime);
        int num = Convert.toInt(Math.ceil(betweenDays.getDays() * (float) 4 / 3 + 1));
        Map<String, BigDecimal> result = new LinkedHashMap<>(num);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), new BigDecimal(0));
            startTime = startTime.plus(1, ChronoUnit.DAYS);
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
    public Map<String, BigDecimal> daySection(LocalDate startTime, LocalDate endTime) {
        return daySection(startTime, endTime, "MM-dd");
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
    public Map<String, BigDecimal> dayHour(LocalDate date, int interval, String pattern) {
        LocalDateTime startTime = LocalDateTime.of(date, LocalTime.of(0, 0, 0));
        LocalDateTime endTime = LocalDateTime.of(date, LocalTime.of(23, 59, 59));
        Map<String, BigDecimal> result = new LinkedHashMap<>(33);
        while (startTime.isBefore(endTime)) {
            result.put(startTime.format(DateTimeFormatter.ofPattern(pattern)), new BigDecimal(0));
            startTime = startTime.plus(interval, ChronoUnit.HOURS);
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
    public Map<String, BigDecimal> dayHour(LocalDate date, String pattern) {
        return dayHour(date, 1, pattern);
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
    public Map<String, BigDecimal> dayHour(LocalDate date, int interval) {
        return dayHour(date, interval, "HH");
    }

    /**
     * 天 24小时Map
     * <p>
     * 根据某一天构建固定间隔小时数的Map 默认一小时
     *
     * @param date 某天日期
     * @return Map
     */
    public Map<String, BigDecimal> dayHour(LocalDate date) {
        return dayHour(date, 1, "HH");
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
    public Map<String, BigDecimal> monthDay(int year, int month, String pattern) {
        Map<String, BigDecimal> result = new LinkedHashMap<>(41);
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plus(1, ChronoUnit.MONTHS);
        while (startDate.isBefore(endDate)) {
            result.put(startDate.format(DateTimeFormatter.ofPattern(pattern)), new BigDecimal(0));
            startDate = startDate.plus(1, ChronoUnit.DAYS);
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
    public Map<String, BigDecimal> monthDay(int year, int month) {
        return monthDay(year, month, "MM-dd");
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
    public Map<String, BigDecimal> yearMonth(int year, String pattern) {
        Map<String, BigDecimal> result = new LinkedHashMap<>(17);
        for (int i = 1; i <= ParamConstant.MONTH_COUNT; i++) {
            LocalDate date = LocalDate.of(year, i, 1);
            result.put(date.format(DateTimeFormatter.ofPattern(pattern)), new BigDecimal(0));
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
    public Map<String, BigDecimal> yearMonth(int year) {
        return yearMonth(year, "MM");
    }
}
