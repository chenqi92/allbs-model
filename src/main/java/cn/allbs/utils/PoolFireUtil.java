package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
 * 池火灾模型计算
 *
 * @author ChenQi
 */
@UtilityClass
public class PoolFireUtil {

    /**
     * 池火灾模型计算
     *
     * @param s  观察者距液池中心的距离与火焰半径的比
     * @param h  火焰高度与火焰半径的比
     * @param x  目标储罐离液池中心的水平距离
     * @param n  效率因子
     * @param m  单位池面积质量燃烧率 kg/(m2·s)
     * @param d  液池直径
     * @param hc 液体燃烧热，kJ/kg
     * @return 火焰表面平均辐射强度 kW/m2
     */
    public Double mudan(double s, double h, double x, double n, double m, double d, double hc) {
        // 大气透过率
        double t = 1 - 0.058 * Math.log(x);
        // 中间系数
        double a = (Math.pow(h, 2) + Math.pow(s, 2) + 1) / (2 * s);
        // 中间系数
        double b = (1 + Math.pow(s, 2)) / (2 * s);
        // 无风时垂直圆柱的水平视图因子
        double sqrt = (a + 1) * (s - 1) / (a - 1) / (s + 1);
        double fh = 1 / Math.PI * ((b - 1 / s) / Math.sqrt(Math.pow(b, 2) - 1) * Math.tan(-1) * Math.sqrt((b + 1) * (s - 1) / ((b - 1) * (s + 1))) - (a - 1 / s) / Math.sqrt(Math.pow(a, 2) - 1) * Math.tan(-1) * Math.sqrt(sqrt));
        // 无风时垂直圆柱的竖直视图因子
        double fv = 1 / Math.PI * (1 / s * Math.tan(-1) * h / Math.sqrt(Math.pow(s, 2) - 1) + h / s * (Math.tan(-1) * Math.sqrt((s - 1) / (s + 1)) - a / Math.sqrt(Math.pow(a, 2) - 1) * Math.tan(-1) * Math.sqrt(sqrt)));
        // 几何视图因子
        double f = Math.sqrt(Math.pow(fh, 2) + Math.pow(fv, 2));
        return 0.25 * Math.PI * Math.pow(d, 2) * n * m * hc / (0.25 * Math.PI * Math.pow(d, 2) + Math.PI * d * h) * f * t;
    }

    /**
     * 燃烧速度
     *
     * @param hc 液体燃烧热 J/Kg
     * @param cp 液体比压定热容 J/(kg·K)
     * @param tb 液体沸点 K
     * @param t0 环境温度 K
     * @param h  液体的汽化热 J/Kg
     * @return 单位池面积燃烧率 kg/(m2·s)
     */
    public Double mf(double hc, double cp, double tb, double t0, double h) {
        return 0.001 * hc / (cp * (tb - t0) + h);
    }

    /**
     * 无风时火焰高度
     *
     * @param m  单位池面积质量燃烧率 kg/(m2·s)
     * @param p0 空气密度，kg/m3
     * @param g  重力加速度，取9.8m/s2
     * @param d  液池直径，m
     * @return 火焰高度 m
     */
    public Double fireHeightWithoutWind(double m, double p0, double g, double d) {
        return 42 * d * Math.pow(m / (p0 * Math.sqrt(g * d)), 0.61);
    }

    /**
     * 有风时火焰高度
     *
     * @param m  单位池面积质量燃烧率 kg/(m2·s)
     * @param p0 空气密度，kg/m3
     * @param g  重力加速度，取9.8m/s2
     * @param d  液池直径，m
     * @param u  10米高处的风速，m/s
     * @param pv 可燃液体的蒸气密度，kg/m3
     * @return 火焰高度 m
     */
    public Double fireHeightWithWind(double m, double p0, double g, double d, double u, double pv) {
        double uf = u / Math.pow(g * m * d / pv, 1 / (float) 3);
        return 55 * d * Math.pow(m / (p0 * Math.sqrt(g * d)), 0.67) * Math.pow(uf, -0.21);
    }

    /**
     * 无风时火焰表面平均辐射强度
     *
     * @param n  效率因子 取值为0.13-0.35
     * @param hc 液体燃烧热，kJ/kg
     * @param x  目标储罐离液池中心的水平距离
     * @param m  单位池面积质量燃烧率 kg/(m2·s)
     * @param p0 空气密度，kg/m3
     * @param g  重力加速度，取9.8m/s2
     * @param d  液池直径，m
     * @return 火焰表面平均辐射强度 kW/m2
     */
    public Double countWithoutWind(double n, double hc, double x, double m, double p0, double g, double d) {
        // 火焰可见高度m
        double hei = fireHeightWithoutWind(m, p0, g, d);
        // 火焰高度与火焰半径的比
        double h = 2 * hei / d;
        // 观察者距液池中心的距离与火焰半径的比
        double s = 2 * x / d;
        return mudan(s, h, x, n, m, d, hc);
    }

    /**
     * 有风时火焰表面平均辐射强度
     *
     * @param n  效率因子
     * @param hc 液体燃烧热，kJ/kg
     * @param x  目标储罐离液池中心的水平距离
     * @param m  位池面积质量燃烧率 kg/(m2·s)
     * @param p0 空气密度，kg/m3
     * @param g  重力加速度，取9.8m/s2
     * @param d  液池直径，m
     * @param u  10米高处的风速，m/s
     * @param pv 可燃液体的蒸气密度，kg/m3
     * @return 火焰表面平均辐射强度 kW/m2
     */
    public Double countWithWind(double n, double hc, double x, double m, double p0, double g, double d, double u, double pv) {
        // 火焰可见高度m
        double hei = fireHeightWithWind(m, p0, g, d, u, pv);
        // 火焰高度与火焰半径的比
        double h = 2 * hei / d;
        // 观察者距液池中心的距离与火焰半径的比
        double s = 2 * x / d;
        return mudan(s, h, x, n, m, d, hc);
    }

    /**
     * 热辐射通量
     *
     * @param d  液池直径 m
     * @param h  火焰高度 m
     * @param v  燃烧速度  kg/(m2·s)
     * @param n  效率因子 取值为0.13-0.35
     * @param hc 液体燃烧热，kJ/kg
     * @return 液池燃烧时放出的总热辐射通量 W
     */
    public Double thermalRadiationFlux(double d, double h, double v, double n, double hc) {
        double r = d / 2;
        return (Math.PI * Math.pow(r, 2) + 2 * Math.PI * r * h) * v * n * hc / (72 * Math.pow(v, 0.6) + 1);
    }

    /**
     * 入射热辐射强度
     *
     * @param q  热辐射通量 W
     * @param tc 热传导系数，在无相对理想的数据时，可取值为1
     * @param x  目标点到液池中心距离
     * @return 入射通量 W/m2
     */
    public Double heatRadiationIntensity(double q, double tc, double x) {
        return q * tc / (4 * Math.PI * Math.pow(x, 2));
    }

    /**
     * 无风时火焰表面平均辐射强度
     *
     * @param n  效率因子 取值为0.13-0.35
     * @param hc 液体燃烧热，kJ/kg
     * @param x  目标储罐离液池中心的水平距离
     * @param m  单位池面积质量燃烧率 kg/(m2·s)
     * @param p0 空气密度，kg/m3
     * @param g  重力加速度，取9.8m/s2
     * @param d  液池直径，m
     * @return 火焰表面平均辐射强度 kW/m2
     */
    public Double count(double n, double hc, double x, double m, double p0, double g, double d) {
        // 火焰高度
        double fh = fireHeightWithoutWind(m, p0, g, d);
        // 热辐射通量
        double q = thermalRadiationFlux(d, fh, m, n, hc);
        // 距离x处辐射强度
        return heatRadiationIntensity(q, 1, x);
    }

    /**
     * 根据热射通量计算距离
     *
     * @param q  总热辐射通量 KW
     * @param tc 热传导系数，在无相对理想的数据时，可取值为1
     * @param i  入射通量 kW/m2
     * @return 距离 m
     */
    public Double x(double q, double tc, double i) {
        return Math.sqrt(q * tc / (4 * Math.PI * i));
    }
}
