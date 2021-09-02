package cn.allbs.utils;

import cn.allbs.model.EarthPoint3D;
import cn.allbs.model.Point3D;
import cn.allbs.model.SpacePoint;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.*;

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
            double x = LngLatUtil.getDistance(a.getX(), a.getY(), centerPoint.getX(), centerPoint.getY());
            double c = PoolFireUtil.count(n, hc, x, m, p0, g, d);
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
            double x = LngLatUtil.getDistance(a.getX(), a.getY(), centerPoint.getX(), centerPoint.getY());
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
            double x = LngLatUtil.getDistance(a.getX(), a.getY(), centerPoint.getX(), centerPoint.getY());
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
            double x = LngLatUtil.getDistance(a.getX(), a.getY(), centerPoint.getX(), centerPoint.getY());
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


    /**
     * 不带入扩散系数计算高斯烟羽模型
     *
     * @param q        物料连续泄漏的质量流量，单位kg/s
     * @param ce        平均风速m/s
     * @param h        泄露源源高
     * @param se      风向角度
     * @param ue      扩散原点偏移后经纬度坐标
     * @param step     步长
     * @param z     水平高度
     * @return 空间点中气体浓度
     */
    public List<Map<String, Object>> gaussPlumeWithoutFactor(Double q, Double ce, Double se, Double h, Double[] ue, Integer step, Double z) {
        List<Map<String, Object>> result = new ArrayList<>();
        // 数据校验
        if (se > 360 || se < 0) {
            return null;
        }
        if (ce == null || ce < 0) {
            return null;
        }

        // 扩散原点
        Double[] originMercator = WGS84MercatorToLngLatUtil.lonLatToMercator(ue[1], ue[0]);

        // 计算扩散区域上半部分
        aa:
        for (int a = 0; a <= 10000; a += step) {
            // x方向增值
            Double ne = Convert.toDouble(a);
            bb:
            for (int o = 0; o <= 2000; o += step) {
                // y方向增值
                Double ie = Convert.toDouble(o);
                Integer currLevel = calculate2(q, ne, ie, z, h, ce, Double.valueOf(0));
                if (currLevel <= 0) {
                    break bb;
                }

                // 边界经纬度list加上一个经纬度点
                Double[] spreadMercator = new Double[]{originMercator[0] + ne, originMercator[1] + ie};
                Double[] spreadLnglat = WGS84MercatorToLngLatUtil.mercatorToLonLat(spreadMercator[0], spreadMercator[1]);
                // 旋转
                Double[] spreadRotateLnglat = LngLatUtil.route(ue, spreadLnglat, se);
                Map<String, Object> levLngLatMap = new HashMap<>();
                levLngLatMap.put("level", currLevel);
                levLngLatMap.put("lng", spreadRotateLnglat[0]);
                levLngLatMap.put("lat", spreadRotateLnglat[1]);
                levLngLatMap.put("height", z);
                result.add(levLngLatMap);
            }
        }

        // 计算扩散区域下半部分，同上
        cc:
        for (int a = 0; a <= 10000; a += step) {
            Double ne = Convert.toDouble(a);
            dd:
            for (int o = -step; o >= -4000; o -= step) {
                Double ie = Convert.toDouble(o);
                Integer currLevel = calculate2(q, ne, ie, z, h, ce, Double.valueOf(0));
                if (currLevel <= 0) {
                    break dd;
                }
                // 边界经纬度list加上一个经纬度点
                Double[] spreadMercator = new Double[]{originMercator[0] + ne, originMercator[1] + ie};
                Double[] spreadLnglat = WGS84MercatorToLngLatUtil.mercatorToLonLat(spreadMercator[0], spreadMercator[1]);
                // 旋转
                Double[] spreadRotateLnglat = LngLatUtil.route(ue, spreadLnglat, se);
                Map<String, Object> levLngLatMap = new HashMap<>();
                levLngLatMap.put("level", currLevel);
                levLngLatMap.put("lng", spreadRotateLnglat[0]);
                levLngLatMap.put("lat", spreadRotateLnglat[1]);
                levLngLatMap.put("height", z);
                result.add(0, levLngLatMap);
            }
        }
        return result;
    }

    /**
     * @param q              物料连续泄漏的质量流量，单位kg/s
     * @param ue             扩散原点经纬度坐标
     * @param originMercator 扩散原点墨卡托坐标
     * @param x              x方向增值
     * @param y              y方向增值
     * @param ce             风速
     * @param se             风向角
     * @param s              時間
     */
    public Integer calSpread(double q, Double[] ue, Double[] originMercator, double x, double y, double ce,
                             Double se, int s, double h) {
        Double n = GaussUtil.powerContinuousDiffusionWithoutSigma(q, ce, h, x, y, h);
        // 浓度值最大因子的level
        int level = calLevel(n);
        return level;
    }


    /**
     * 烟羽扩散
     *
     * @param Q 气载污染物源强，即释放率（mg/s）
     * @param x 下风向距离（m）
     * @param y 横截风向距离（m）
     * @param z 距水平的高度（m）
     * @param h 排口高度
     * @param u 平均风速m/s
     * @param t 时间s
     * @return 等级
     */
    public Integer calculate2(Double Q, Double x, Double y, Double z, Double h, Double u, Double t) {
        // 根据大气稳定度等级-获取扩散参数值
        // Double[] spreadArray = getCardinalityArray(pointDto.getRadiationRank(), pointDto.getU());
        Double[] spreadArray = {};
        Double a = ArrayUtil.isNotEmpty(spreadArray) ? spreadArray[0] : 0.527;
        Double b = ArrayUtil.isNotEmpty(spreadArray) ? spreadArray[1] : 0.865;
        Double c = ArrayUtil.isNotEmpty(spreadArray) ? spreadArray[2] : 0.280;
        Double d = ArrayUtil.isNotEmpty(spreadArray) ? spreadArray[3] : 0.900;

        Double σy = a * Math.pow(x, b);
        Double σz = c * Math.pow(x, d);
        Double σx = σy;
        Double C = (Q / (Math.pow(2 * Math.PI, 3 / (float) 2) * σx * σy * σz)) *
                Math.exp(-(Math.pow(y, 2) / (2 * Math.pow(σy, 2)))) *
                (Math.exp(-(Math.pow(z - h, 2) / (2 * Math.pow(σz, 2)))) +
                        Math.exp(-(Math.pow(z + h, 2) / (2 * Math.pow(σz, 2))))
                ) * Math.exp(-(Math.pow(x - u * t, 2) / Math.pow(2 * σx, 2)));

        // 浓度值最大因子的level
        int level = calLevel(C);
        return level;
    }

    /**
     * 计算因子level
     *
     * @param aa 因子浓度值
     * @return
     */
    public int calLevel(double aa) {
        // 扩散点浓度阈值
        aa = aa * 1e3;
        Double[] ge = new Double[]{1.5, 18D, 90D, 180D, 300D, 3e3};
        int level = 0;
        for (int r = 0; r < ge.length; r++) {
            // 第一个阈值
            if (0 == r) {
                if (aa < ge[r + 1] && aa >= ge[r]) {
                    level = r;
                }
            } else {
                // 最后一个阈值
                if (r == ge.length - 1) {
                    if (aa >= ge[r]) {
                        level = r;
                    }
                } else {
                    if (aa < ge[r + 1] && aa >= ge[r]) {
                        level = r;
                    }
                }
            }
        }
        return level;
    }

    /**
     * 不带入扩散系数计算高斯烟羽模型
     *
     * @param q     物料连续泄漏的质量流量，单位kg/s
     * @param u     平均风速m/s
     * @param h     泄露源源高
     * @param angle 风向角度
     * @param lng   起火中心点经度
     * @param lat   起火中心点纬度
     * @return 空间点中气体浓度
     */
    public Set<Map<String, Object>> gaussPlumeWithoutFactorInCesium(double q, double u, double angle, double h, double lng, double lat, int t, double xStep, double yStep, double zStep, double xLimit, double yLimit, double zLimit) {
        final double cos = Math.cos(Math.PI * (360 - angle - 90) / 180);
        final double sin = Math.sin(Math.PI * (360 - angle - 90) / 180);
        // 获取所有坐标点
        Set<Point3D> point3DS = SpaceGeometryUtil.takeAllPoints(xStep, yStep, zStep, xLimit, yLimit, zLimit, false, true, true);
        // 获取墨卡托坐标点
        Double[] mercator = WGS84MercatorToLngLatUtil.lonLatToMercator(lat, lng);
        Double[] mercatorPoint = {0D, 0 - yLimit};
        // 东北角坐标
        Double[] dbPoint = {mercator[0], mercator[1] + yLimit};
        Set<Map<String, Object>> points = new HashSet<>();
        point3DS.forEach(a -> {
            double x = Math.max(a.getX() - u * t, xStep);
            Double gaussValue = GaussUtil.highPowerContinuousDiffusion(q, u, h, x, a.getY(), a.getZ(), t);
            if (!ObjectUtil.isValidIfNumber(gaussValue)) {
                return;
            }
            BigDecimal value = NumberUtil.round(gaussValue, 5);
            if (value.compareTo(new BigDecimal(0)) == 0) {
                return;
            }
            Map<String, Object> pointMap = new HashMap<>();
            // 偏移角度
            Double pointX = a.getX() + mercator[0] - dbPoint[0];
            Double pointY = a.getY() + mercator[1] - dbPoint[1];
            pointMap.put("x", (pointX - mercatorPoint[0]) * cos - (pointY - mercatorPoint[1]) * sin + mercatorPoint[0]);
            pointMap.put("y", (pointY - mercatorPoint[1]) * cos + (pointX - mercatorPoint[0]) * sin + mercatorPoint[1]);
            pointMap.put("value", value);
            System.out.println(pointMap);
            points.add(pointMap);
        });
        return points;
    }
}
