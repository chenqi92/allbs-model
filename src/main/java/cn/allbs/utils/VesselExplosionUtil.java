package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 容器爆炸模型
 *
 * @author ChenQi
 */
@UtilityClass
public class VesselExplosionUtil {

    /**
     * 冲击波超压值
     *
     * @param p 容器内气体的绝对压力（MPa）
     * @param v V为容器的容积（m3）
     * @param r 爆炸点距防护目标的距离（m）
     * @return 目标位置冲击波超压值 kPa
     */
    public Double count(double p, double v, double r) {
        double q = 5 * p * v / 7 * (1 - Math.pow(0.1 / p, 0.3));
        return (14 * q / Math.pow(r, 3) + 4 * Math.pow(q, 2 / (float) 3) / Math.pow(r, 2)) * 100;
    }
}
