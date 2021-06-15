package cn.allbs.utils;

import cn.hutool.core.util.ObjectUtil;
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
     * @param t1 闪蒸蒸发时间 单位为s 为NUll时不计入总气云质量
     * @param t2 热量蒸发时间，单位为s 为NUll时不计入总气云质量
     * @param t3 从液体泄漏 到液体全部处理完毕的时间，单位为s 为NUll时不计入总气云质量
     * @return 形成的气云质量 kg
     */
    public Double totalEvaporation(Double qm, Double cp, Double tt, Double tb, Double hv, Double a1, Double t0, Double h, Double t, Double u, Double r, Double m, Double t1, Double t2, Double t3) {
        double total = 0;
        // 闪蒸气云质量质量计算
        if (ObjectUtil.isAllNotEmpty(qm, cp, tt, tb, hv, t1)) {
            total = total + onlyFlash(qm, cp, tt, tb, hv, t1);
        }
        // 热量增发气云质量计算
        if (ObjectUtil.isAllNotEmpty(a1, t0, h, t, t2, tb)) {
            total = total + onlyHeatOfEvaporation(a1, t0, h, t, t2, tb);
        }
        // 质量蒸发气云质量计算
        if (ObjectUtil.isAllNotEmpty(t0, u, r, m, t3)) {
            total = total + onlyQualityOfEvaporation(t0, u, r, m, t3);
        }
        return total;
    }

    /**
     * 仅出现过热液体闪蒸蒸发情况
     *
     * @param qm 物质泄漏速率 kg/s
     * @param cp 泄漏液体的定压热容 kJ/(kg.K)
     * @param tt 储存温度，单位为K
     * @param tb 泄漏液体的沸点，单位为K
     * @param hv 泄漏液体的蒸发热，单位为J/kg
     * @param t1 闪蒸蒸发时间 单位为s
     * @return 形成的气云质量 kg
     */
    public Double onlyFlash(double qm, double cp, double tt, double tb, double hv, double t1) {
        return flash(qm, cp, tt, tb, hv) * t1;
    }

    /**
     * 仅出现热量增发的情况
     *
     * @param a1 液池面积 m2
     * @param t0 环境温度 K
     * @param h  液体蒸发热 J/kg
     * @param t  蒸发时间 s
     * @param t2 热量蒸发时间，单位为s
     * @param tb 泄漏液体的沸点，单位为K
     * @return 形成的气云质量 kg
     */
    public Double onlyHeatOfEvaporation(double a1, double t0, double h, double t, double t2, double tb) {
        return heatOfEvaporation(a1, t0, tb, h, t) * t2;
    }

    /**
     * 仅出现质量蒸发的情况
     *
     * @param t0 环境温度 K
     * @param u  风速 m/s
     * @param r  液池半径 m
     * @param m  泄漏物质分子量
     * @param t3 从液体泄漏 到液体全部处理完毕的时间，单位为s
     * @return 形成的气云质量 kg
     */
    public Double onlyQualityOfEvaporation(double t0, double u, double r, double m, double t3) {
        return qualityOfEvaporation(t0, u, r, m) * t3;
    }
}
