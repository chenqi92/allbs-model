package cn.allbs.utils;

import cn.allbs.constant.PatternConstant;
import cn.allbs.constant.StringPoolConstant;
import cn.allbs.enums.MathExtentEnum;
import lombok.experimental.UtilityClass;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * 判断是否在区间中
 * <p>
 * 注意工具类中使用的是Double型精度有限！
 *
 * @author ChenQi
 */
@UtilityClass
public class IntervalUtil {

    /**
     * 判断dataValue是否在interval区间范围内
     *
     * @param dataValue 数值类型的
     * @param interval  正常的数学区间，包括无穷大等，如：(1,3)、more than 5%、(-∞,6]、(125%,135%)U(70%,80%)
     * @return {@code true}：表示dataValue在区间interval范围内，false：表示dataValue不在区间interval范围内
     */
    public static boolean isInTheInterval(String dataValue, String interval) {
        //将区间和dataValue转化为可计算的表达式
        String formula = getFormulaByAllInterval(dataValue, interval);
        ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");
        try {
            //计算表达式
            return (Boolean) jse.eval(formula);
        } catch (Exception t) {
            return false;
        }
    }

    /**
     * 将所有阀值区间转化为公式：如
     * [75,80)   转换为                  dataValue  &lt; 80 && dataValue &gt;= 75
     * (125%,135%)U(70%,80%)   转换为     (dataValue  &lt; 1.35 && dataValue &gt; 1.25) || (dataValue  &lt; 0.8 && dataValue &gt; 0.7)
     *
     * @param dataValue 传入值
     * @param interval  形式如：(125%,135%)U(70%,80%)
     */
    private static String getFormulaByAllInterval(String dataValue, String interval) {
        StringBuilder buff = new StringBuilder();
        //如：（125%,135%）U (70%,80%)
        for (String limit : interval.split(StringPoolConstant.U)) {
            buff.append("(").append(getFormulaByInterval(dataValue, limit)).append(")").append("||");
        }
        String allLimitIntel = buff.toString();
        int index = allLimitIntel.lastIndexOf("||");
        allLimitIntel = allLimitIntel.substring(0, index);
        return allLimitIntel;
    }

    /**
     * 将整个阀值区间转化为公式：如
     * 145)      转换为         dataValue  &lt; 145
     * [75,80)   转换为        dataValue  &lt; 80 && dataValue &gt;= 75
     *
     * @param dataValue 传入值
     * @param interval  形式如：145),[75,80)
     */
    private static String getFormulaByInterval(String dataValue, String interval) {
        StringBuilder buff = new StringBuilder();
        //如：[75,80)、≥80
        for (String halfInterval : interval.split(StringPoolConstant.COMMA)) {
            buff.append(getFormulaByHalfInterval(halfInterval, dataValue)).append(" && ");
        }
        String limitIntel = buff.toString();
        int index = limitIntel.lastIndexOf(" && ");
        limitIntel = limitIntel.substring(0, index);
        return limitIntel;
    }

    /**
     * 将半个阀值区间转化为公式：如
     * 145)      转换为         dataValue  &lt; 145
     * ≥80%      转换为         dataValue &gt;= 0.8
     * [130     转换为         dataValue &gt;= 130
     * <80%     转换为         dataValue  &lt; 0.8
     *
     * @param halfInterval 形式如：145)、&gt;=80%、[130、 &lt;80%
     * @param dateValue    传入值
     * @return dataValue  &lt; 145
     */
    private static String getFormulaByHalfInterval(String halfInterval, String dateValue) {
        halfInterval = halfInterval.trim();
        //包含无穷大则不需要公式
        if (halfInterval.contains(StringPoolConstant.INFINITY)) {
            return "1 == 1";
        }
        StringBuilder formula = new StringBuilder();
        String data;
        String opera;
        //表示判断方向（如>）在前面 如：≥80%
        if (halfInterval.matches(PatternConstant.INTERVAL_SYMBOL)) {
            opera = halfInterval.substring(0, 1);
            data = halfInterval.substring(1);
        } else {//[130、145)
            opera = halfInterval.substring(halfInterval.length() - 1);
            data = halfInterval.substring(0, halfInterval.length() - 1);
        }
        double value = dealPercent(data);
        formula.append(dateValue).append(" ").append(opera).append(" ").append(value);
        String a = formula.toString();
        //转化特定字符
        return a.replace("[", ">=").replace("(", ">").replace("]", "<=").replace(")", "<").replace("≤", "<=").replace("≥", ">=");
    }

    /**
     * 去除百分号，转为小数
     *
     * @param str 可能含百分号的数字
     * @return 表达式
     */
    private static double dealPercent(String str) {
        double d;
        if (str.contains(StringPoolConstant.PERCENT)) {
            str = str.substring(0, str.length() - 1);
            d = Double.parseDouble(str) / 100;
        } else {
            d = Double.parseDouble(str);
        }
        return d;
    }

    /**
     * 全闭区间
     * <p>
     * 判断是否在全闭区间内
     *
     * @param min      最小值
     * @param max      最大值
     * @param checkNum 校验值
     * @return true 区间内 false 不在区间内
     */
    public static boolean checkInAllCloseInterval(Double min, Double max, Double checkNum) {
        if (checkNum == null) {
            return true;
        }
        return IntervalUtil.isInTheInterval(checkNum.toString(), MathExtentEnum.getEqExpressAllClose(min, max));
    }

    /**
     * 全开区间
     * <p>
     * 判断是否在全开区间内
     *
     * @param min      最小值
     * @param max      最大值
     * @param checkNum 校验值
     * @return true 区间内 false 不在区间内
     */
    public static boolean checkInAllOpenInterval(Double min, Double max, Double checkNum) {
        if (checkNum == null) {
            return true;
        }
        return IntervalUtil.isInTheInterval(checkNum.toString(), MathExtentEnum.getEqExpressAllOpen(min, max));
    }

    /**
     * 左闭右开区间
     * <p>
     * 判断是否在左闭右开区间内
     *
     * @param min      最小值
     * @param max      最大值
     * @param checkNum 校验值
     * @return true 区间内 false 不在区间内
     */
    public static boolean checkInLeftCloseInterval(Double min, Double max, Double checkNum) {
        if (checkNum == null) {
            return true;
        }
        return IntervalUtil.isInTheInterval(checkNum.toString(), MathExtentEnum.getEqExpressRightOpenOnly(min, max));
    }

    /**
     * 左开右闭区间
     * <p>
     * 判断是否在左开右闭区间区间内
     *
     * @param min      最小值
     * @param max      最大值
     * @param checkNum 校验值
     * @return true 区间内 false 不在区间内
     */
    public static boolean checkInRightCloseInterval(Double min, Double max, Double checkNum) {
        if (checkNum == null) {
            return true;
        }
        return IntervalUtil.isInTheInterval(checkNum.toString(), MathExtentEnum.getEqExpressLeftOpenOnly(min, max));
    }
}
