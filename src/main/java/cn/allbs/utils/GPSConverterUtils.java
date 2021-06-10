package cn.allbs.utils;

import lombok.experimental.UtilityClass;

/**
     * 百度坐标（BD09）、国测局坐标（火星坐标，GCJ02）、和WGS84坐标系之间的转换的工具
 * <p>
 * WGS84: Google Earth采用，Google Map中国范围外使用
 * GCJ02: 火星坐标系，中国国家测绘局制定的坐标系统，由WGS84机密后的坐标。Google Map中国和搜搜地图使用，高德
 * BD09:百度坐标，GCJ02机密后的坐标系
 *
 * @author ChenQi
 */
@UtilityClass
public class GPSConverterUtils {

    /**
     * 地球半径
     */
    private static final double A = 6378137.0D;

    static double x_pi = Math.PI * 3000.0 / 180.0;
    /**
     * 扁率
     */
    static double ee = 0.00669342162296594323;

    /**
     * 百度坐标系(BD-09)转WGS坐标
     *
     * @param lng 百度坐标纬度
     * @param lat 百度坐标经度
     * @return WGS84坐标数组
     */
    public static double[] bd09toWgs84(double lng, double lat) {
        double[] gcj = bd09toGcj02(lng, lat);
        return gcj02toWgs84(gcj[0], gcj[1]);
    }

    /**
     * WGS坐标转百度坐标系(BD-09)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 百度坐标数组
     */
    public static double[] wgs84toBd09(double lng, double lat) {
        double[] gcj = wgs84toGcj02(lng, lat);
        return gcj02toBd09(gcj[0], gcj[1]);
    }

    /**
     * 火星坐标系(GCJ-02)转百度坐标系(BD-09)
     * <p>
     * 谷歌、高德——&lt;百度
     *
     * @param lng 火星坐标经度
     * @param lat 火星坐标纬度
     * @return 百度坐标数组
     */
    public static double[] gcj02toBd09(double lng, double lat) {
        double z = Math.sqrt(lng * lng + lat * lat) + 0.00002 * Math.sin(lat * x_pi);
        double theta = Math.atan2(lat, lng) + 0.000003 * Math.cos(lng * x_pi);
        double bdLng = z * Math.cos(theta) + 0.0065;
        double bdLat = z * Math.sin(theta) + 0.006;
        return new double[]{bdLng, bdLat};
    }

    /**
     * 百度坐标系(BD-09)转火星坐标系(GCJ-02)
     * <p>
     * 百度——&lt;谷歌、高德
     *
     * @param bdLon 百度坐标纬度
     * @param bdLat 百度坐标经度
     * @return 火星坐标数组
     */
    public static double[] bd09toGcj02(double bdLon, double bdLat) {
        double x = bdLon - 0.0065;
        double y = bdLat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double ggLng = z * Math.cos(theta);
        double ggLat = z * Math.sin(theta);
        return new double[]{ggLng, ggLat};
    }

    /**
     * WGS84转GCJ02(火星坐标系)
     *
     * @param lng WGS84坐标系的经度
     * @param lat WGS84坐标系的纬度
     * @return 火星坐标数组
     */
    public static double[] wgs84toGcj02(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dLat = transFormLat(lng - 105.0, lat - 35.0);
        double dLng = transFormLng(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((A * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
        dLng = (dLng * 180.0) / (A / sqrtMagic * Math.cos(radLat) * Math.PI);
        double mgLat = lat + dLat;
        double mgLng = lng + dLng;
        return new double[]{mgLng, mgLat};
    }

    /**
     * GCJ02(火星坐标系)转GPS84
     *
     * @param lng 火星坐标系的经度
     * @param lat 火星坐标系纬度
     * @return WGS84坐标数组
     */
    public static double[] gcj02toWgs84(double lng, double lat) {
        if (outOfChina(lng, lat)) {
            return new double[]{lng, lat};
        }
        double dLat = transFormLat(lng - 105.0, lat - 35.0);
        double dLng = transFormLng(lng - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * Math.PI;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((A * (1 - ee)) / (magic * sqrtMagic) * Math.PI);
        dLng = (dLng * 180.0) / (A / sqrtMagic * Math.cos(radLat) * Math.PI);
        double mgLat = lat + dLat;
        double mgLng = lng + dLng;
        return new double[]{lng * 2 - mgLng, lat * 2 - mgLat};
    }

    /**
     * 纬度转换
     *
     * @param lng 经度
     * @param lat 纬度
     * @return 纬度转换值
     */
    public static double transFormLat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat + 0.2 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * Math.PI) + 20.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lat * Math.PI) + 40.0 * Math.sin(lat / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(lat / 12.0 * Math.PI) + 320 * Math.sin(lat * Math.PI / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 经度转换
     *
     * @param lng 经度
     * @param lat 纬度
     * @return 经度转换值
     */
    public static double transFormLng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret += (20.0 * Math.sin(6.0 * lng * Math.PI) + 20.0 * Math.sin(2.0 * lng * Math.PI)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(lng * Math.PI) + 40.0 * Math.sin(lng / 3.0 * Math.PI)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(lng / 12.0 * Math.PI) + 300.0 * Math.sin(lng / 30.0 * Math.PI)) * 2.0 / 3.0;
        return ret;
    }

    /**
     * 判断是否在国内，不在国内不做偏移
     *
     * @param lng 经度
     * @param lat 纬度
     * @return true 国外 false 国内
     */
    public static boolean outOfChina(double lng, double lat) {
        if (lng < 72.004 || lng > 137.8347) {
            return true;
        } else {
            return lat < 0.8293 || lat > 55.8271;
        }
    }
}
