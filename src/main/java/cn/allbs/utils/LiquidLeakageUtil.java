package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 液体泄漏模型
 *
 * @author ChenQi
 */
@UtilityClass
public class LiquidLeakageUtil {

    /**
     * 质量流率
     *
     * @param p  液体密度 kg/m3
     * @param a  泄漏孔面积 m2
     * @param hl 泄漏孔上方液体高度 m
     * @return 与泄漏时间相乘后即为泄漏的液体质量 kg/s
     */
    public Double count(double p, double a, double hl) {
        return 0.65 * p * a * Math.sqrt(19.6 * hl);
    }
}
