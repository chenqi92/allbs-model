package cn.allbs.enums;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 数学区间定义
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum MathExtentEnum {

    /**
     * 双侧无界
     */
    NO_LIMIT("(-∞, +∞)=R", "1=1"),

    /**
     * 有界区间-闭区间
     */
    IN_SECTION_LEFT_RIGHT("[a,b]:{x∈R:a≤x≤b}", "[{},{}]"),

    /**
     * 左闭右开区间
     */
    IN_SECTION_LEFT_ONLY("[a,b)={x∈R:a≤x<b}", "[{},{})"),

    /**
     * 左开右闭区间
     */
    IN_SECTION_RIGHT_ONLY("(a,b]={x∈R:a<x≤b}", "({},{}]"),

    /**
     * 开区间
     */
    IN_SECTION("(a,b)={x∈R:a<x<b}", "({},{})"),

    /**
     * 左闭
     */
    LEFT_CLOSED("[a,+∞)={x∈R:x≥a}", "[{},+∞)"),

    /**
     * 左开
     */
    LEFT_OPEN("(a,+∞)={x∈R:x>a}", "({},+∞)"),

    /**
     * 右闭
     */
    RIGHT_CLOSED("(-∞, b]={x∈R:x≤b}", "(-∞, {}]"),

    /**
     * 右开
     */
    RIGHT_OPEN("(-∞, b)={x∈R:x<b}", "(-∞, {})");

    /**
     * 数学定义
     */
    private final String pattern;

    /**
     * eq表达式
     */
    private final String expression;

    /**
     * 全闭区间
     * <p>
     * 获取区间计算表达式
     *
     * @param min 最小值
     * @param max 最大值
     * @return 区间计算表达式
     */
    public static String getEqExpressAllClose(Double min, Double max) {
        if (min != null && max != null) {
            return StrUtil.format(IN_SECTION_LEFT_RIGHT.getExpression(), min, max);
        }
        if (min != null) {
            return StrUtil.format(LEFT_CLOSED.getExpression(), min);
        }
        if (max != null) {
            return StrUtil.format(RIGHT_CLOSED.getExpression(), max);
        }
        return NO_LIMIT.getExpression();
    }

    /**
     * 全开区间
     * <p>
     * 获取区间计算表达式
     *
     * @param min 最小值
     * @param max 最大值
     * @return 区间表达式
     */
    public static String getEqExpressAllOpen(Double min, Double max) {
        if (min != null && max != null) {
            return StrUtil.format(IN_SECTION.getExpression(), min, max);
        }
        if (min != null) {
            return StrUtil.format(LEFT_OPEN.getExpression(), min);
        }
        if (max != null) {
            return StrUtil.format(RIGHT_OPEN.getExpression(), max);
        }
        return NO_LIMIT.getExpression();
    }

    /**
     * 左开右闭
     * <p>
     * 获取区间计算表达式
     *
     * @param min 最小值
     * @param max 最大值
     * @return 区间表达式
     */
    public static String getEqExpressLeftOpenOnly(Double min, Double max) {
        if (min != null && max != null) {
            return StrUtil.format(IN_SECTION_RIGHT_ONLY.getExpression(), min, max);
        }
        if (min != null) {
            return StrUtil.format(LEFT_OPEN.getExpression(), min);
        }
        if (max != null) {
            return StrUtil.format(RIGHT_CLOSED.getExpression(), max);
        }
        return NO_LIMIT.getExpression();
    }

    /**
     * 左闭右开
     * <p>
     * 获取区间计算表达式
     *
     * @param min 最小值
     * @param max 最大值
     * @return 区间表达式
     */
    public static String getEqExpressRightOpenOnly(Double min, Double max) {
        if (min != null && max != null) {
            return StrUtil.format(IN_SECTION_LEFT_ONLY.getExpression(), min, max);
        }
        if (min != null) {
            return StrUtil.format(LEFT_CLOSED.getExpression(), min);
        }
        if (max != null) {
            return StrUtil.format(RIGHT_OPEN.getExpression(), max);
        }
        return NO_LIMIT.getExpression();
    }

}
