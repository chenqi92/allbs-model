package cn.allbs.utils;

import cn.allbs.model.EarthPoint3D;
import cn.allbs.model.Point3D;
import cn.allbs.model.SpacePoint;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;

/**
 * 模型计算的工具类
 *
 * @author ChenQi
 */
@UtilityClass
public class ModelUtil {

    /**
     * 池火灾事故计算模型
     *
     * @param g              重力加速度，取9.8m/s2
     * @param p0             空气密度，kg/m3
     * @param n              效率因子 取值为0.13-0.35
     * @param hc             液体燃烧热，kJ/kg
     * @param m              单位池面积质量燃烧率 kg/(m2·s)
     * @param d              液池直径，m
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param extendDistance 子扩散距离用于渲染
     * @return 空间点(经纬度, 高度) 和所在位置浓度
     */
    public Set<SpacePoint> poolFire(double g, double p0, double n, double hc, double m, double d, double lng, double lat, double height, double distance, double extendDistance) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<Point3D> points = SpaceGeometryUtil.earthBatchPeak(centerPoint, distance, extendDistance);
        points.forEach(a -> {
            if (a.getZ() < 0) {
                a.setZ(0);
            }
            double x = a.distance(centerPoint);
            double c = PoolFireUtil.countWithoutWind(n, hc, x, m, p0, g, d);
            poolFirePoints.add(new SpacePoint(a, c));
        });
        return poolFirePoints;
    }

    /**
     * 水平方向喷射火模型计算
     *
     * @param hc             燃烧热 kJ/kg
     * @param m              质量流速 kg/s
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param extendDistance 子扩散距离用于渲染
     * @return 空间点中热辐射通量 Kw/m2
     */
    public Set<SpacePoint> horizontalFire(double hc, double m, double lng, double lat, double height, double distance, double extendDistance) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<Point3D> points = SpaceGeometryUtil.earthBatchPeak(centerPoint, distance, extendDistance);
        points.forEach(a -> {
            if (a.getZ() < 0) {
                a.setZ(0);
            }
            double x = a.distance(centerPoint);
            double c = HorizontalJetFireUtil.count(hc, x, m);
            poolFirePoints.add(new SpacePoint(a, c));
        });
        return poolFirePoints;
    }

    /**
     * 容器爆炸模型计算
     *
     * @param p              容器内气体的绝对压力（MPa）
     * @param v              V为容器的容积（m3）
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param extendDistance 子扩散距离用于渲染
     * @return 空间点中冲击波的超压值
     */
    public Set<SpacePoint> vesselExplosion(double p, double v, double lng, double lat, double height, double distance, double extendDistance) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<Point3D> points = SpaceGeometryUtil.earthBatchPeak(centerPoint, distance, extendDistance);
        points.forEach(a -> {
            if (a.getZ() < 0) {
                a.setZ(0);
            }
            double x = a.distance(centerPoint);
            double c = VesselExplosionUtil.count(p, v, x);
            poolFirePoints.add(new SpacePoint(a, c));
        });
        return poolFirePoints;
    }

    /**
     * 蒸汽云爆炸模型
     *
     * @param qm             物质泄漏速率 kg/s
     * @param cp             泄漏液体的定压热容 kJ/(kg.K)
     * @param tt             储存温度，单位为K
     * @param tb             泄漏液体的沸点，单位为K
     * @param hv             泄漏液体的蒸发热，单位为J/kg
     * @param a1             液池面积 m2
     * @param t0             环境温度 K
     * @param h              液体蒸发热 J/kg
     * @param t              蒸发时间 s
     * @param u              风速 m/s
     * @param r              液池半径 m
     * @param m              泄漏物质分子量
     * @param t1             闪蒸蒸发时间 单位为s 为NUll时不计入总气云质量
     * @param t2             热量蒸发时间，单位为s 为NUll时不计入总气云质量
     * @param t3             从液体泄漏 到液体全部处理完毕的时间，单位为s 为NUll时不计入总气云质量
     * @param q              气云燃烧热 J.kg-1
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param extendDistance 子扩散距离用于渲染
     * @return 空间点中无约束蒸气云爆炸冲击波正相最大超压 Kpa
     */
    public Set<SpacePoint> vaporCloudExplosion(Double qm, Double cp, Double tt, Double tb, Double hv, Double a1, Double t0, Double h, Double t, Double u, Double r, Double m, Double t1, Double t2, Double t3, double q, double lng, double lat, double height, double distance, double extendDistance) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<Point3D> points = SpaceGeometryUtil.earthBatchPeak(centerPoint, distance, extendDistance);
        points.forEach(a -> {
            if (a.getZ() < 0) {
                a.setZ(0);
            }
            // 计算距离
            double x = a.distance(centerPoint);
            // 计算气云质量
            double w = EvaporationUtil.totalEvaporation(qm, cp, tt, tb, hv, a1, t0, h, t, u, r, m, t1, t2, t3);
            // 蒸汽云爆炸
            double c = VaporCloudExplosionUtil.count(w, q, x);
            poolFirePoints.add(new SpacePoint(a, c));
        });
        return poolFirePoints;
    }

    /**
     * 高斯烟团计算模型
     *
     * @param ws             平均风速 m/s
     * @param t              扩散时间 s
     * @param q              瞬时排放的物料质量 kg
     * @param angle          风向 度
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param step 步长
     * @return 空间点中气体浓度
     */
    public Set<SpacePoint> gaussSmokeRegiment(double ws, double t, double q, double angle, double lng, double lat, double height, double distance, double step) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<EarthPoint3D> points = SpaceGeometryUtil.totalEarthBatchPeakDetailWithoutWd(centerPoint, distance, angle, step);
        points.forEach(a -> {
            if (a.getHeight() < 0) {
                a.setZ(-(distance + a.getHeight()));
                a.setHeight(0.0);
            }
            if (a.getX() == 0) {
                return;
            }
            // 气体泄露
            double c = NumberUtil.round(GaussUtil.smokeConcentration(q, ws, t, Math.abs(a.getX()), Math.abs(a.getY()), Math.abs(a.getZ())), 3).doubleValue();
            if (ObjectUtil.isNotNull(c) && c != 0) {
                poolFirePoints.add(new SpacePoint(a, c));
            }
        });
        return poolFirePoints;
    }

    /**
     * 带入扩散系数计算高斯烟羽模型
     *
     * @param ws             平均风速 m/s 只考虑横向风
     * @param q              连续泄露的质量流量 kg/s
     * @param l              太阳辐射等级
     * @param h              泄漏源源高
     * @param angle          风向角度
     * @param lng            起火中心点经度
     * @param lat            起火中心点纬度
     * @param height         起火中心点高度
     * @param distance       扩散距离
     * @param step 步长
     * @return 空间点中气体浓度
     */
    public Set<SpacePoint> gaussPlumeWithFactor(double ws, double q, Integer l, double angle, double h, double lng, double lat, double height, double distance, double step) {
        Point3D centerPoint = new Point3D(lng, lat, height);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        Set<EarthPoint3D> points = SpaceGeometryUtil.EarthBatchPeakDetailToWd(centerPoint, distance, angle, step);
        // 查询所有待计算的点位
        points.forEach(a -> {
            if (a.getHeight() < 0) {
                a.setZ(-(distance + a.getHeight()));
                a.setHeight(0.0);
                // 计算地面点源气体泄露
                double c = NumberUtil.round(GaussUtil.allGroundReflection(q, ws, h, a.getX(), Math.abs(a.getY()), l), 3).doubleValue();
                poolFirePoints.add(new SpacePoint(a, c));
            } else {
                // 计算高架点源气体泄露
                double c = NumberUtil.round(GaussUtil.highPowerContinuousDiffusion(q, ws, h, a.getX(), Math.abs(a.getY()), a.getZ(), l), 3).doubleValue();
                poolFirePoints.add(new SpacePoint(a, c));
            }

        });
        return poolFirePoints;
    }

    /**
     * 不带入扩散系数计算高斯烟羽模型
     *
     * @param q        物料连续泄漏的质量流量，单位kg/s
     * @param u        平均风速m/s
     * @param h        泄露源源高
     * @param angle    风向角度
     * @param lng      起火中心点经度
     * @param lat      起火中心点纬度
     * @param distance 扩散距离
     * @param step     步长
     * @return 空间点中气体浓度
     */
    public Set<SpacePoint> gaussPlumeWithoutFactor(double q, double u, double angle, double h, double lng, double lat, double distance, double step) {
        Point3D centerPoint = new Point3D(lng, lat, h);
        Set<SpacePoint> poolFirePoints = new HashSet<>();
        // 查询所有待计算的点位
        Set<EarthPoint3D> points = SpaceGeometryUtil.EarthBatchPeakDetailToWd(centerPoint, distance, angle, step);
        points.forEach(a -> {
            if (a.getHeight() < 0) {
                a.setZ(-(distance + a.getHeight()));
                a.setHeight(0.0);
            }
            // 气体泄露
            double c = NumberUtil.round(GaussUtil.powerContinuousDiffusionWithoutSigma(q, u, h, Math.abs(a.getX()), Math.abs(a.getY()), Math.abs(a.getZ())), 3).doubleValue();
            if (c != 0) {
                poolFirePoints.add(new SpacePoint(a, c));
            }
        });
        return poolFirePoints;
    }
}
