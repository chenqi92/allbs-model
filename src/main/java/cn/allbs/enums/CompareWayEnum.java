package cn.allbs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 比较方式枚举
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum CompareWayEnum {

    /**
     * 比较方式
     */
    SECTION("求区间值", "[]"),

    LESS_THAN("小于等于", "≤"),

    GATHER_THAN("大于等于", "≥");

    /**
     * 描述
     */
    private final String description;

    /**
     * 数学计算方式
     */
    private final String mathMethod;
}
