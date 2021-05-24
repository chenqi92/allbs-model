package cn.allbs.utils;

import cn.allbs.constant.CommonConstant;
import cn.allbs.constant.ParamConstant;
import cn.allbs.enums.SolarRadiationEnum;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.NumberUtil;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高斯模型
 * <p>
 * 高斯烟羽 + 高斯烟团
 *
 * @author ChenQi
 */
@UtilityClass
public class GaussUtil {

    /**
     * y轴扩散系数
     *
     * @param x 风向轴上空间点到源的距离 m
     * @param q 大气稳定度
     * @return 水平扩散参数 m
     */
    private double getSigmaY(double x, String q) {
        double a;
        double r;
        if (SolarRadiationEnum.A.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.901074;
                r = 0.425809;
            } else {
                a = 0.850934;
                r = 0.602052;
            }
        } else if (SolarRadiationEnum.B.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.914370;
                r = 0.281846;
            } else {
                a = 0.865014;
                r = 0.396353;
            }
        } else if (SolarRadiationEnum.BC.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.919325;
                r = 0.229500;
            } else {
                a = 0.875086;
                r = 0.314238;
            }
        } else if (SolarRadiationEnum.C.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.924279;
                r = 0.177154;
            } else {
                a = 0.885157;
                r = 0.232123;
            }
        } else if (SolarRadiationEnum.CD.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.926849;
                r = 0.143940;
            } else {
                a = 0.886940;
                r = 0.189396;
            }
        } else if (SolarRadiationEnum.D.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.929418;
                r = 0.110726;
            } else {
                a = 0.888723;
                r = 0.146669;
            }
        } else if (SolarRadiationEnum.DE.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.925118;
                r = 0.0985631;
            } else {
                a = 0.892794;
                r = 0.124308;
            }
        } else if (SolarRadiationEnum.E.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.920818;
                r = 0.0864001;
            } else {
                a = 0.896864;
                r = 0.101947;
            }
        } else {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.929418;
                r = 0.0553634;
            } else {
                a = 0.888723;
                r = 0.0733348;
            }
        }
        return Math.pow(x, a) * r;
    }

    /**
     * z轴扩散系数
     *
     * @param x 风向轴上空间点到源的距离 m
     * @param q 大气稳定度
     * @return 垂直扩散参数 m
     */
    private double getSigmaZ(double x, String q) {
        double a;
        double r;
        if (SolarRadiationEnum.A.getLevel().equals(q)) {
            if (x <= ParamConstant.THREE_HUNDRED) {
                a = 1.12154;
                r = 0.0799904;
            } else if (x > ParamConstant.THREE_HUNDRED || x <= ParamConstant.FIVE_HUNDRED) {
                a = 1.51360;
                r = 0.00854771;
            } else {
                a = 2.10881;
                r = 0.000211545;
            }
        } else if (SolarRadiationEnum.B.getLevel().equals(q)) {
            if (x <= ParamConstant.FIVE_HUNDRED) {
                a = 0.964435;
                r = 0.127190;
            } else {
                a = 0.109356;
                r = 0.057025;
            }
        } else if (SolarRadiationEnum.BC.getLevel().equals(q)) {
            if (x <= ParamConstant.FIVE_HUNDRED) {
                a = 0.941015;
                r = 0.114682;
            } else {
                a = 1.00770;
                r = 0.0757182;
            }
        } else if (SolarRadiationEnum.C.getLevel().equals(q)) {
            a = 0.917595;
            r = 0.106803;
        } else if (SolarRadiationEnum.CD.getLevel().equals(q)) {
            if (x <= ParamConstant.TWO_THOUSAND) {
                a = 0.838628;
                r = 0.126152;
            } else if (x > ParamConstant.TWO_THOUSAND || x <= ParamConstant.TEN_THOUSAND) {
                a = 0.756410;
                r = 0.235667;
            } else {
                a = 0.815575;
                r = 0.136659;
            }
        } else if (SolarRadiationEnum.D.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.826212;
                r = 0.104634;
            } else if (x > ParamConstant.THOUSAND || x <= ParamConstant.TEN_THOUSAND) {
                a = 0.632023;
                r = 0.400167;
            } else {
                a = 0.55536;
                r = 0.810763;
            }
        } else if (SolarRadiationEnum.DE.getLevel().equals(q)) {
            if (x <= ParamConstant.TWO_THOUSAND) {
                a = 0.776864;
                r = 0.111771;
            } else if (x > ParamConstant.TWO_THOUSAND || x <= ParamConstant.TEN_THOUSAND) {
                a = 0.572347;
                r = 0.5289922;
            } else {
                a = 0.499149;
                r = 1.03810;
            }
        } else if (SolarRadiationEnum.E.getLevel().equals(q)) {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.788370;
                r = 0.0927529;
            } else if (x > ParamConstant.THOUSAND || x <= ParamConstant.TEN_THOUSAND) {
                a = 0.565188;
                r = 0.433384;
            } else {
                a = 0.414743;
                r = 1.73241;
            }
        } else {
            if (x <= ParamConstant.THOUSAND) {
                a = 0.784400;
                r = 0.0620765;
            } else if (x > ParamConstant.THOUSAND || x <= ParamConstant.TEN_THOUSAND) {
                a = 0.525969;
                r = 0.370015;
            } else {
                a = 0.322659;
                r = 2.40691;
            }
        }
        return Math.pow(x, a) * r;
    }


    /**
     * 地面点源扩散 计算方式
     *
     * @param q 物料连续泄漏的质量流量，单位kg/s
     * @param u 平均风速m/s
     * @param x x距离
     * @param y y距离
     * @param z z距离
     * @param l 太阳辐射等级
     * @return 空间上某点的污染物浓度 mg/m3
     */
    public double groundPointSource(double q, double u, double x, double y, double z, Integer l) {
        // y轴标准差
        double sigmaY = getSigmaY(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        // z轴标准差
        double sigmaZ = getSigmaZ(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        return (q / (Math.PI * u * sigmaY * sigmaZ)) * Math.exp((Math.pow(y, 2) / Math.pow(sigmaY, 2) + Math.pow(z, 2) / Math.pow(sigmaZ, 2)) / 2 * -1) * Math.pow(10, 6);
    }


    /**
     * 高架点源扩散模式计算
     *
     * @param q 物料连续泄漏的质量流量，单位kg/s
     * @param u 平均风速m/s
     * @param h 泄露源源高
     * @param x x距离
     * @param y y距离
     * @param z z距离
     * @param l 太阳辐射等级
     * @return 空间上某点的污染物浓度 mg/m3
     */
    public double highPowerContinuousDiffusion(double q, double u, double h, double x, double y, double z, Integer l) {
        // y轴标准差
        double sigmaY = getSigmaY(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        // z轴标准差
        double sigmaZ = getSigmaZ(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        return (q / (2 * Math.PI * u * sigmaY * sigmaZ)) * Math.exp(Math.pow(y, 2) * -1 / 2 / Math.pow(sigmaY, 2)) * (Math.exp(Math.pow(z - h, 2) * -1 / 2 / Math.pow(sigmaZ, 2)) + Math.exp(Math.pow(z + h, 2) * -1 / 2 / Math.pow(sigmaZ, 2))) * Math.pow(10, 6);
    }

    /**
     * 高斯烟羽不带入扩散系数
     *
     * @param q 物料连续泄漏的质量流量，单位kg/s
     * @param u 平均风速m/s
     * @param h 泄露源源高
     * @param x x距离
     * @param y y距离
     * @param z z距离
     * @return 空间上某点的污染物浓度 mg/m3
     */
    public double powerContinuousDiffusionWithoutSigma(double q, double u, double h, double x, double y, double z) {
        return q / (0.09 * Math.PI * u * Math.pow(x, 1.7)) * Math.exp(0 - Math.pow(y, 2) / 0.08 / Math.pow(x, 1.8)) * (Math.exp(0 - Math.pow(z - h, 2) / 0.08 / Math.pow(x, 1.6)) + Math.exp(0 - Math.pow(z + h, 2) / 0.08 / Math.pow(x, 1.6))) * Math.pow(10, 6);
    }

    /**
     * 地面全部反射时的地面浓度
     *
     * @param q 物料连续泄漏的质量流量，单位kg/s
     * @param u 平均风速m/s
     * @param h 泄露源源高
     * @param x x距离
     * @param y y距离
     * @param l 太阳辐射等级
     * @return 空间上某点的污染物浓度 mg/m3
     */
    public double allGroundReflection(double q, double u, double h, double x, double y, Integer l) {
        // y轴标准差
        double sigmaY = getSigmaY(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        // z轴标准差
        double sigmaZ = getSigmaZ(x, SolarRadiationEnum.solarRadiationLevel(l, u));
        return (q / (Math.PI * u * sigmaY * sigmaZ)) * (Math.exp((Math.pow(y, 2) / Math.pow(sigmaY, 2) + Math.pow(h, 2) / Math.pow(sigmaZ, 2)) / 2 * -1)) * Math.pow(10, 6);
    }


    /**
     * 高斯烟团模型(短时间内形成毒气云团，扩散时间远大于泄漏时间的扩散) 不包含系数计算
     *
     * @param q 瞬时排放的物料质量 kg
     * @param u 平均风速 m/s
     * @param t 扩散时间 s
     * @param x 空间点 x距离
     * @param y 空间点y距离
     * @param z 空间点 z距离
     * @return 气体浓度 mg/m3
     */
    public double smokeConcentration(double q, double u, double t, double x, double y, double z) {
        final double v = 2 * Math.pow(0.2 * Math.pow(x, 0.9), 2);
        return (q / (0.09 * 0.2 * 25 * Math.pow(x, 2.6))) * Math.exp(0 - Math.pow(x - u * t, 2) / v - Math.pow(y, 2) / v - Math.pow(z, 2) / (2 * Math.pow(0.2 * Math.pow(x, 0.8), 2))) * Math.pow(10, 6);
    }

    /**
     * 烟云抬升高度
     *
     * @param vs 气云释放速度 单位m/s
     * @param d  泄露出口直径 单位m
     * @param ws 环境风速 单位m/s
     * @return 烟云抬升高度 m
     */
    public double liftingHeightOfSmokeCloud(double vs, double d, double ws) {
        return 2.4 * vs * d / ws;
    }

    /**
     * 高速烟团模型
     *
     * @param centerLng 中心位置经度
     * @param centerLat 中心位置维度
     * @param ws        风速
     * @param t         秒
     * @param h         高度
     * @param q         连续排放泄露的速录
     * @param angle     风向角度
     * @return 气体爆破点点为中心点的一个空间四方体的8个角的坐标及浓度
     */
    public List<Map<String, Double>> getGaussSmokePoints(double centerLng, double centerLat, double ws, double t, double h, double q, double angle) {
        // 默认设置扩散速度为1，步长为1m
        return getGaussSmokePoints(centerLng, centerLat, ws, t, h, q, 1, 1, angle);
    }

    /**
     * 高速烟团模型
     *
     * @param centerLng 中心位置经度
     * @param centerLat 中心位置维度
     * @param ws        风速
     * @param t         秒
     * @param h         高度
     * @param q         连续排放泄露的速录
     * @param speed     播放速度
     * @param step      步长
     * @param angle     风向角度
     * @return 气体爆破点点为中心点的一个空间四方体的8个角的坐标及浓度
     */
    public List<Map<String, Double>> getGaussSmokePoints(double centerLng, double centerLat, double ws, double t, double h, double q, int speed, double step, double angle) {
        // 平移距离x
        double totalX = ws * t * speed;
        // 平移距离/步长向上取整
        List<Double> xRange = CollUtil.toList(totalX, -totalX);
        List<Double> yRange = CollUtil.toList(totalX, -totalX);
        List<Double> zRange = CollUtil.toList(h);
        int section = Convert.toInt(Math.ceil(totalX / step));
        for (int index = 0; index < section; index++) {
            if (index != 0) {
                CommonUtil.notContainAdd(xRange, index * step);
                CommonUtil.notContainAdd(xRange, -index * step);
            }
            CommonUtil.notContainAdd(yRange, index * step);
            CommonUtil.notContainAdd(yRange, -index * step);
            CommonUtil.notContainAdd(zRange, h + index * step);
            if (h - index * step <= 0) {
                CommonUtil.notContainAdd(zRange, 0D);
            } else {
                CommonUtil.notContainAdd(zRange, h - index * step);
            }
        }
        List<Map<String, Double>> cMapList = new ArrayList<>();
        for (Double aDouble : xRange) {
            for (Double value : yRange) {
                for (Double item : zRange) {
                    Map<String, Double> cMap = new HashMap<>(5);
                    double x = aDouble;
                    double y = value;
                    double z = item;
                    cMap.put(CommonConstant.HEIGHT, z);
                    double c;
                    c = NumberUtil.round(smokeConcentration(q, ws, t, x, y, z), 3).doubleValue();
                    if (c == 0) {
                        continue;
                    }
                    cMap.put(CommonConstant.POLLUTANT_CONCENTRATION, c);
                    lngLatMapCount(x, y, centerLng, centerLat, angle, cMap);
                    cMapList.add(cMap);
                }
            }
        }
        return cMapList;
    }


    /**
     * 高斯烟羽模型-高架点源 包含系数计算 不指定速度和步长取值
     *
     * @param centerLng 事故发生时的经度
     * @param centerLat 事故发生时的纬度
     * @param ws        平均风速 m/s 只考虑横向风
     * @param h         烟囱高度 m
     * @param q         连续泄露的质量流量 kg/s
     * @param d         泄露出口直径 单位m
     * @param w         相对分子质量
     * @param l         太阳辐射等级
     * @param t         泄露后经过的时间 s
     * @param angle     风向以正北为0度 顺时针
     * @return 以泄露点为中心点的半个空间四方体正方向上的18个角的坐标及浓度 Mg/m3
     */
    public List<Map<String, Double>> getGaussPlumePointsInElevated(double centerLng, double centerLat, double ws, double h, double q, double d, double w, Integer l, int t, double angle) {
        // 默认速度一倍, 默认步长为1m
        return getGaussPlumePointsInElevated(centerLng, centerLat, ws, h, q, d, w, l, t, 1, angle, 1);
    }

    /**
     * 高斯烟羽模型-高架点源 包含系数计算 指定速度和步长取值
     *
     * @param centerLng 事故发生时的经度
     * @param centerLat 事故发生时的纬度
     * @param ws        平均风速 m/s 只考虑横向风
     * @param h         烟囱高度 m
     * @param q         连续泄露的质量流量 kg/s
     * @param d         泄露出口直径 单位m
     * @param w         相对分子质量
     * @param l         太阳辐射等级
     * @param t         泄露后经过的时间 s
     * @param speed     播放速度倍率默认为1
     * @param angle     风向以正北为0度 顺时针
     * @param step      步长
     * @return 以泄露点为中心点的半个空间四方体正方向上的18个角的坐标及浓度 Mg/m3
     */
    public List<Map<String, Double>> getGaussPlumePointsInElevated(double centerLng, double centerLat, double ws, double h, double q, double d, double w, Integer l, int t, int speed, double angle, double step) {
        // 计算气云释放速度
        double vs = q / (1000 / 22.4 * w / 1000) / Math.PI * Math.pow(d / 2, 2);
        // 泄露源的源高
        double totalH = liftingHeightOfSmokeCloud(vs, d, ws) + h;
        // 平移距离x
        double totalX = ws * t * speed;
        // 平移距离/步长向上取整
        List<Double> xRange = CollUtil.toList(totalX);
        List<Double> yRange = CollUtil.toList(totalX, -totalX);
        List<Double> zRange = CollUtil.toList(totalH);
        int section = Convert.toInt(Math.ceil(totalX / step));
        for (int index = 0; index < section; index++) {
            if (index != 0) {
                CommonUtil.notContainAdd(xRange, index * step);
            }
            CommonUtil.notContainAdd(yRange, index * step);
            CommonUtil.notContainAdd(yRange, -index * step);
            CommonUtil.notContainAdd(zRange, totalH + index * step);
            if (totalH - index * step <= 0) {
                CommonUtil.notContainAdd(zRange, 0D);
            } else {
                CommonUtil.notContainAdd(zRange, totalH - index * step);
            }
        }
        List<Map<String, Double>> cMapList = new ArrayList<>();
        for (Double aDouble : xRange) {
            for (Double value : yRange) {
                for (Double item : zRange) {
                    Map<String, Double> cMap = new HashMap<>(5);
                    double x = aDouble;
                    double y = value;
                    double z = item;
                    cMap.put(CommonConstant.HEIGHT, z);
                    double c;
                    if (z == 0) {
                        c = NumberUtil.round(allGroundReflection(q, ws, totalH, x, Math.abs(y), l), 3).doubleValue();
                    } else {
                        c = NumberUtil.round(highPowerContinuousDiffusion(q, ws, totalH, x, Math.abs(y), z, l), 3).doubleValue();
                    }
                    if (c == 0) {
                        continue;
                    }
                    cMap.put(CommonConstant.POLLUTANT_CONCENTRATION, c);
                    lngLatMapCount(x, y, centerLng, centerLat, angle, cMap);
                    cMapList.add(cMap);
                }
            }
        }
        return cMapList;
    }

    /**
     * 高斯烟羽模型-地面点源 包含系数计算
     *
     * @param centerLng 事故发生时的经度
     * @param centerLat 事故发生时的纬度
     * @param ws        平均风速 m/s 只考虑横向风
     * @param q         连续泄露的质量流量 kg/s
     * @param l         太阳辐射等级
     * @param t         泄露后经过的时间 s
     * @param angle     风向以正北为0度 顺时针
     * @return 以泄露点为中心点的半个空间四方体正方向上的18个角的坐标及浓度 Mg/m3
     */
    public List<Map<String, Double>> getGaussPlumePointsInFloor(double centerLng, double centerLat, double ws, double q, Integer l, int t, double angle) {
        // 默认播放速度为1倍速, 默认步长为1米
        return getGaussPlumePointsInFloor(centerLng, centerLat, ws, q, l, t, 1, angle, 1);
    }

    /**
     * 高斯烟羽模型-地面点源 包含系数计算
     *
     * @param centerLng 事故发生时的经度
     * @param centerLat 事故发生时的纬度
     * @param ws        平均风速 m/s 只考虑横向风
     * @param q         连续泄露的质量流量 kg/s
     * @param l         太阳辐射等级
     * @param t         泄露后经过的时间 s
     * @param speed     播放速度倍率默认为1
     * @param angle     风向以正北为0度 顺时针
     * @param step      步长
     * @return 以泄露点为中心点的半个空间四方体正方向上的18个角的坐标及浓度 Mg/m3
     */
    public List<Map<String, Double>> getGaussPlumePointsInFloor(double centerLng, double centerLat, double ws, double q, Integer l, int t, int speed, double angle, double step) {
        // 平移距离x
        double totalX = ws * t * speed;
        // 平移距离/步长向上取整
        List<Double> xRange = CollUtil.toList(totalX);
        List<Double> yRange = CollUtil.toList(0D, totalX, -totalX);
        List<Double> zRange = CollUtil.toList(0D, totalX);
        int section = Convert.toInt(Math.ceil(totalX / step));
        for (int index = 1; index < section; index++) {
            CommonUtil.notContainAdd(xRange, index * step);
            CommonUtil.notContainAdd(yRange, index * step);
            CommonUtil.notContainAdd(yRange, -index * step);
            CommonUtil.notContainAdd(zRange, index * step);
        }
        List<Map<String, Double>> cMapList = new ArrayList<>();
        for (Double aDouble : xRange) {
            for (Double value : yRange) {
                for (Double item : zRange) {
                    Map<String, Double> cMap = new HashMap<>(5);
                    double x = aDouble;
                    double y = value;
                    double z = item;
                    cMap.put(CommonConstant.HEIGHT, z);
                    double c = NumberUtil.round(groundPointSource(q, ws, x, Math.abs(y), z, l), 3).doubleValue();
                    if (c == 0) {
                        break;
                    }
                    cMap.put(CommonConstant.POLLUTANT_CONCENTRATION, c);
                    lngLatMapCount(x, y, centerLng, centerLat, angle, cMap);
                    cMapList.add(cMap);
                }
            }
        }
        return cMapList;
    }

    /**
     * 计算高斯烟羽模型-不考虑大气稳定度等系数条件
     *
     * @param centerLng 起始经度
     * @param centerLat 起始纬度
     * @param ws        平均风速 m/s
     * @param q         源强
     * @param t         泄露时间
     * @param h         高度
     * @param angle     风向角度
     * @return 不考虑大气稳定度等系数条件空间点污染物浓度
     */
    public List<Map<String, Double>> gaussianPlume(double centerLng, double centerLat, double ws, double q, int t, double h, double angle) {
        return gaussianPlume(centerLng, centerLat, ws, q, t, 1, h, angle, 1);
    }

    /**
     * 计算高斯烟羽模型-不考虑大气稳定度等系数条件
     *
     * @param centerLng 起始经度
     * @param centerLat 起始纬度
     * @param ws        风速
     * @param q         原强
     * @param t         经过时间
     * @param speed     速度
     * @param h         源高
     * @param angle     角度
     * @param step      步长
     * @return 不考虑大气稳定度等系数条件污染物浓度
     */
    public List<Map<String, Double>> gaussianPlume(double centerLng, double centerLat, double ws, double q, int t, int speed, double h, double angle, double step) {
        // 平移距离x
        double totalX = ws * t * speed;
        // 平移距离/步长向上取整
        List<Double> xRange = CollUtil.toList(totalX);
        List<Double> yRange = CollUtil.toList(totalX, -totalX);
        List<Double> zRange = CollUtil.toList(h);
        int section = Convert.toInt(Math.ceil(totalX / step));
        for (int index = 0; index < section; index++) {
            if (index != 0) {
                CommonUtil.notContainAdd(xRange, index * step);
            }
            CommonUtil.notContainAdd(yRange, index * step);
            CommonUtil.notContainAdd(yRange, -index * step);
            CommonUtil.notContainAdd(zRange, h + index * step);
            if (h - index * step <= 0) {
                CommonUtil.notContainAdd(zRange, 0D);
            } else {
                CommonUtil.notContainAdd(zRange, h - index * step);
            }
        }
        List<Map<String, Double>> cMapList = new ArrayList<>();
        for (Double aDouble : xRange) {
            for (Double value : yRange) {
                for (Double item : zRange) {
                    Map<String, Double> cMap = new HashMap<>(5);
                    double x = aDouble;
                    double y = value;
                    double z = item;
                    cMap.put(CommonConstant.HEIGHT, z);
                    double c;
                    c = NumberUtil.round(powerContinuousDiffusionWithoutSigma(q, ws, h, x, y, z), 3).doubleValue();
                    if (c == 0) {
                        continue;
                    }
                    cMap.put(CommonConstant.POLLUTANT_CONCENTRATION, c);
                    lngLatMapCount(x, y, centerLng, centerLat, angle, cMap);
                    cMapList.add(cMap);
                }
            }
        }
        return cMapList;
    }

    /**
     * 计算经纬独所在的点信息
     *
     * @param x         x轴平移距离
     * @param y         y轴平移距离
     * @param centerLng 起始经度
     * @param centerLat 起始纬度
     * @param angle     风向角
     * @param cMap      储存信息map
     */
    private void lngLatMapCount(double x, double y, double centerLng, double centerLat, double angle, Map<String, Double> cMap) {
        Map<String, Double> lngLatMap = new HashMap<>(4);
        // 计算经纬度
        if (x == 0 && y == 0) {
            lngLatMap.put(CommonConstant.LONGITUDE, centerLng);
            lngLatMap.put(CommonConstant.LATITUDE, centerLat);
        } else if (x == 0) {
            lngLatMap = LngLatUtil.calLocationByDistanceAndLocationAndDirection(90 - angle, centerLng, centerLat, y);
        } else if (y == 0) {
            lngLatMap = LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle, centerLng, centerLat, x);
        } else {
            double dis = Math.sqrt(x * x + y * y);
            double ag = Math.toDegrees(Math.atan2(Math.abs(y), x));
            if (y > 0) {
                lngLatMap = LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle - ag, centerLng, centerLat, dis);
            } else {
                lngLatMap = LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + ag, centerLng, centerLat, dis);
            }
        }
        cMap.put(CommonConstant.LONGITUDE, lngLatMap.get(CommonConstant.LONGITUDE));
        cMap.put(CommonConstant.LATITUDE, lngLatMap.get(CommonConstant.LATITUDE));
    }
}
