package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 蒸汽云爆炸模型
 *
 * @author ChenQi
 */
@UtilityClass
public class VaporCloudExplosionUtil {

    /**
     * 无约束蒸气云爆炸冲击波
     *
     * @param w 泄漏物料形成的气云质量 kg
     * @param q 气云燃烧热 J.kg-1
     * @param d 蒸气云中心距离 m
     * @return 无约束蒸气云爆炸冲击波正相最大超压 Kpa
     */
    public Double count(double w, double q, double d) {
        return 101.3 * Math.pow(Math.E, -0.9216 - 1.5058 * (Math.log(d) - 1 / (float) 3 * Math.log(0.07 * w * q) + 4.6183));
    }
}
