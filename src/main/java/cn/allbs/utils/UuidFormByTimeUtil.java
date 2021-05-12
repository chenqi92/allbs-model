package cn.allbs.utils;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

/**
 * 将当前时间转换为短字符的uuid 最大支持62年 62年后人都没了谁还去维护这代码呢==
 *
 * @author ChenQi
 */
@UtilityClass
public class UuidFormByTimeUtil {

    /**
     * 62位字符定义
     */
    String[] scale = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 设定当前年份为第一年
     */
    private final int currentYear = 2021;

    /**
     * 时间转换为更短的唯一标识符
     *
     * @param headPrefix   首位前缀
     * @param middlePrefix 标识符
     * @return 唯一标识符
     */
    public String uuid(String headPrefix, String middlePrefix) {
        return uuid(currentYear, headPrefix, middlePrefix);
    }

    /**
     * 时间转换为更短的唯一标识符
     *
     * @param startYear    起始年份
     * @param headPrefix   首位前缀
     * @param middlePrefix 标识符
     * @return 唯一标识符
     */
    public String uuid(int startYear, String headPrefix, String middlePrefix) {
        LocalDateTime now = LocalDateTime.now();
        startYear = Math.max(currentYear, startYear);
        int year = Math.max(now.getYear() - startYear, 0);
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        return StrUtil.concat(false, headPrefix, middlePrefix, scale[year], scale[month], scale[day], scale[hour], scale[minute], scale[second]);
    }
}
