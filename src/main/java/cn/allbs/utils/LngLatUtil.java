package cn.allbs.utils;

import cn.allbs.constant.CommonConstant;
import cn.hutool.core.util.NumberUtil;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 经纬度操作
 *
 * @author ChenQi
 * @date 2021/4/20
 */
@UtilityClass
public class LngLatUtil {

    /**
     * 地球半径
     */
    private static final double WGS84_EARTH_RADIUS = 6378137.0D;

    /**
     * WGS84 扁率
     */
    private static final double WGS84_OBLATENESS = 298.257223563D;

    /**
     * 计算经纬度(带扁率校准)
     *
     * @param startLng 起始经度
     * @param startLat 起始纬度
     * @param endLng   结束经度
     * @param endLat   结束纬度
     * @return 距离 米
     */
    public double getDistanceOfMeter(double startLng, double startLat, double endLng, double endLat) {
        double f = 1.0D / WGS84_OBLATENESS;
        double b = (1.0D - f) * WGS84_EARTH_RADIUS;
        double a2b2b2 = (Math.pow(WGS84_EARTH_RADIUS, 2) - Math.pow(b, 2)) / Math.pow(b, 2);
        double omega = Math.toRadians(endLng) - Math.toRadians(startLng);
        double atPhi1 = Math.atan((1.0D - f) * Math.tan(Math.toRadians(startLat)));
        double sinU1 = Math.sin(atPhi1);
        double cosU1 = Math.cos(atPhi1);
        double atanPhi2 = Math.atan((1.0D - f) * Math.tan(Math.toRadians(endLat)));
        double sinU2 = Math.sin(atanPhi2);
        double cosU2 = Math.cos(atanPhi2);
        double sinU1sinU2 = sinU1 * sinU2;
        double cosU1sinU2 = cosU1 * sinU2;
        double sinU1cosU2 = sinU1 * cosU2;
        double cosU1cosU2 = cosU1 * cosU2;
        double lambda = omega;
        double A = 0.0D;
        double sigma = 0.0D;
        double deltaSigma = 0.0D;
        for (int i = 0; i < 20; ++i) {
            double lambda0 = lambda;
            double sinLambda = Math.sin(lambda);
            double cosLambda = Math.cos(lambda);
            double sin2Sigma = cosU2 * sinLambda * cosU2 * sinLambda + (cosU1sinU2 - sinU1cosU2 * cosLambda) * (cosU1sinU2 - sinU1cosU2 * cosLambda);
            double sinSigma = Math.sqrt(sin2Sigma);
            double cosSigma = sinU1sinU2 + cosU1cosU2 * cosLambda;
            sigma = Math.atan2(sinSigma, cosSigma);
            double sinAlpha = BigDecimal.valueOf(sin2Sigma).equals(BigDecimal.valueOf(0)) ? 0.0D : cosU1cosU2 * sinLambda / sinSigma;
            double alpha = Math.asin(sinAlpha);
            double cosAlpha = Math.cos(alpha);
            double cos2alpha = cosAlpha * cosAlpha;
            double cos2SigmaM = BigDecimal.valueOf(cos2alpha).equals(BigDecimal.valueOf(0)) ? 0.0D : cosSigma - 2.0D * sinU1sinU2 / cos2alpha;
            double u2 = cos2alpha * a2b2b2;
            double cos2SigmaM2 = cos2SigmaM * cos2SigmaM;
            A = 1.0D + u2 / 16384.0D * (4096.0D + u2 * (-768.0D + u2 * (320.0D - 175.0D * u2)));
            double B = u2 / 1024.0D * (256.0D + u2 * (-128.0D + u2 * (74.0D - 47.0D * u2)));
            deltaSigma = B * sinSigma * (cos2SigmaM + B / 4.0D * (cosSigma * (-1.0D + 2.0D * cos2SigmaM2) - B / 6.0D * cos2SigmaM * (-3.0D + 4.0D * sin2Sigma) * (-3.0D + 4.0D * cos2SigmaM2)));
            double C = f / 16.0D * cos2alpha * (4.0D + f * (4.0D - 3.0D * cos2alpha));
            lambda = omega + (1.0D - C) * f * sinAlpha * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1.0D + 2.0D * cos2SigmaM2)));
            double change = Math.abs((lambda - lambda0) / lambda);
            if (i > 1 && change < 1.0E-13D) {
                break;
            }
        }
        return b * A * (sigma - deltaSigma);
    }

    /**
     * 计算经纬度（不带扁率校准）
     *
     * @param startLng 起始经度
     * @param startLat 起始纬度
     * @param endLng   结束经度
     * @param endLat   结束纬度
     * @return 相差距离
     */
    public double getDistance(double startLng, double startLat, double endLng, double endLat) {
        double radLat1 = Math.toRadians(startLat);
        double radLat2 = Math.toRadians(endLat);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(startLng) - Math.toRadians(endLng);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * WGS84_EARTH_RADIUS;
        return s;
    }

    /**
     * 根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴
     *
     * @param angle    角度，从正北顺时针方向开始计算
     * @param startLng 起始点经度
     * @param startLat 起始点纬度
     * @param distance 距离，单位m
     * @return 经纬度map
     */
    public Map<String, Double> calLocationByDistanceAndLocationAndDirection(double angle, double startLng, double startLat, double distance) {
        Map<String, Double> result = new HashMap<>(5);
        //将距离转换成经度的计算公式
        double sigma = distance / WGS84_EARTH_RADIUS;
        // 转换为radian，否则结果会不正确
        angle = Math.toRadians(angle);
        startLng = Math.toRadians(startLng);
        startLat = Math.toRadians(startLat);
        double lat = Math.asin(Math.sin(startLat) * Math.cos(sigma) + Math.cos(startLat) * Math.sin(sigma) * Math.cos(angle));
        double lng = startLng + Math.atan2(Math.sin(angle) * Math.sin(sigma) * Math.cos(startLat), Math.cos(sigma) - Math.sin(startLat) * Math.sin(lat));
        // 转为正常的10进制经纬度
        lng = Math.toDegrees(lng);
        lat = Math.toDegrees(lat);
        result.put(CommonConstant.LONGITUDE, NumberUtil.round(lng, 6).doubleValue());
        result.put(CommonConstant.LATITUDE, NumberUtil.round(lat, 6).doubleValue());
        return result;
    }
}
