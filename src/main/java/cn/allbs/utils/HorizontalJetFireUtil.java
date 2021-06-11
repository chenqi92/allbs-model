package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 水平方向喷射火模型
 *
 * @author ChenQi
 */
@UtilityClass
public class HorizontalJetFireUtil {

    /**
     * 计算热辐射通量
     *
     * @param hc 燃烧热 kJ/kg
     * @param x  距离 m
     * @param m  质量流速 kg/s
     * @return 距离x处接收的热辐射通量 Kw/m2
     */
    public Double count(double hc, double x, double m) {
        return 0.15 * hc * m * (1 - 0.06 * Math.log(x)) / (12000 * Math.pow(x, 2));
    }
}
