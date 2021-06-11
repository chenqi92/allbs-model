package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 蒸发
 *
 * @author ChenQi
 */
@UtilityClass
public class EvaporationUtil {

    /**
     * 闪蒸蒸发速率
     *
     * @param qm 物质泄漏速率 kg/s
     * @param cp 泄漏液体的定压热容 kJ/(kg.K)
     * @param tt 储存温度，单位为K
     * @param tb 泄漏液体的沸点，单位为K
     * @param hv 泄漏液体的蒸发热，单位为J/kg
     * @return 热液体闪蒸蒸发速率，单位为kg/s
     */
    public Double flash(double qm, double cp, double tt, double tb, double hv) {
        return qm * cp * (tt - tb) / hv;
    }

    /**
     * 热量蒸发速率
     * <p>
     * 环境温度高于液体沸点时发生，如35摄氏度的环境温度下，沸点为20摄氏度的液体泄漏
     *
     * @param a1 液池面积 m2
     * @param t0 环境温度 K
     * @param tb 液体沸点 K
     * @param h  液体蒸发热 J/kg
     * @param t  蒸发时间 s
     * @return 热量蒸发速率 kg/s
     */
    public Double heatOfEvaporation(double a1, double t0, double tb, double h, double t) {
        return 1.1 * a1 * (t0 - tb) / (h * Math.sqrt(4 * Math.pow(10, -7) * t));
    }

    /**
     * 质量蒸发速率
     *
     * @param t0 环境温度 K
     * @param u  风速 m/s
     * @param r  液池半径 m
     * @param m  泄漏物质分子量
     * @return 质量蒸发速率 kg/s
     */
    public Double qualityOfEvaporation(double t0, double u, double r, double m) {
        return 0.05 * m / (8.314 * t0) * Math.pow(u, 3.5) * Math.pow(r, 9);
    }

    /**
     * 液池蒸发总量
     *
     * @param qm 物质泄漏速率 kg/s
     * @param cp 泄漏液体的定压热容 kJ/(kg.K)
     * @param tt 储存温度，单位为K
     * @param tb 泄漏液体的沸点，单位为K
     * @param hv 泄漏液体的蒸发热，单位为J/kg
     * @param a1 液池面积 m2
     * @param t0 环境温度 K
     * @param h  液体蒸发热 J/kg
     * @param t  蒸发时间 s
     * @param u  风速 m/s
     * @param r  液池半径 m
     * @param m  泄漏物质分子量
     * @param t1 闪蒸蒸发时间 单位为s
     * @param t2 热量蒸发时间，单位为s
     * @param t3 从液体泄漏 到液体全部处理完毕的时间，单位为s
     * @return 形成的气云质量 kg
     */
    public Double totalEvaporation(double qm, double cp, double tt, double tb, double hv, double a1, double t0, double h, double t, double u, double r, double m, double t1, double t2, double t3) {
        return flash(qm, cp, tt, tb, hv) * t1 + heatOfEvaporation(a1, t0, tb, h, t) * t2 + qualityOfEvaporation(t0, u, r, m) * t3;
    }
}
