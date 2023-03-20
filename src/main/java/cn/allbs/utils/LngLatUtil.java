package cn.allbs.utils;

import cn.allbs.constant.CommonConstant;
import cn.allbs.constant.ParamConstant;
import cn.allbs.enums.CoordinateSystemEnum;
import cn.allbs.model.Point3D;
import lombok.experimental.UtilityClass;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经纬度操作
 *
 * @author ChenQi
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
    private static final double WGS84_OBLIQUENESS = 298.257223563D;

    /**
     * 计算两个经纬度位置距离(带扁率校准)
     *
     * @param startLng 起始经度
     * @param startLat 起始纬度
     * @param endLng   结束经度
     * @param endLat   结束纬度
     * @return 距离 米
     */
    public double getDistanceOfMeter(double startLng, double startLat, double endLng, double endLat) {
        double f = 1.0D / WGS84_OBLIQUENESS;
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
        double a = 0.0D;
        double sigma = 0.0D;
        double deltaSigma = 0.0D;
        for (int i = 0; i < ParamConstant.OBLIQUENESS_NUM; ++i) {
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
            a = 1.0D + u2 / 16384.0D * (4096.0D + u2 * (-768.0D + u2 * (320.0D - 175.0D * u2)));
            double B = u2 / 1024.0D * (256.0D + u2 * (-128.0D + u2 * (74.0D - 47.0D * u2)));
            deltaSigma = B * sinSigma * (cos2SigmaM + B / 4.0D * (cosSigma * (-1.0D + 2.0D * cos2SigmaM2) - B / 6.0D * cos2SigmaM * (-3.0D + 4.0D * sin2Sigma) * (-3.0D + 4.0D * cos2SigmaM2)));
            double C = f / 16.0D * cos2alpha * (4.0D + f * (4.0D - 3.0D * cos2alpha));
            lambda = omega + (1.0D - C) * f * sinAlpha * (sigma + C * sinSigma * (cos2SigmaM + C * cosSigma * (-1.0D + 2.0D * cos2SigmaM2)));
            double change = Math.abs((lambda - lambda0) / lambda);
            if (i > 1 && change < 1.0E-13D) {
                break;
            }
        }
        return b * a * (sigma - deltaSigma);
    }

    /**
     * 计算经纬度距离（不带扁率校准, 默认为WGS84坐标）
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
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * WGS84_EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        return s;
    }

    /**
     * 计算经纬度 带入坐标系进行判断后计算
     *
     * @param startLng             起始经度
     * @param startLat             起始纬度
     * @param endLng               结束经度
     * @param endLat               结束纬度
     * @param coordinateSystemEnum 坐标系枚举
     * @return 相差距离
     */
    public double getDistance(double startLng, double startLat, double endLng, double endLat, CoordinateSystemEnum coordinateSystemEnum) {
        // 火星坐标系转化
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.GCJ02)) {
            double[] start = GPSConverterUtils.gcj02toWgs84(startLng, startLat);
            double[] end = GPSConverterUtils.gcj02toWgs84(endLng, endLat);
            return getDistance(start[0], start[1], end[0], end[1]);
        }
        // 百度坐标系转换
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.BD09)) {
            double[] start = GPSConverterUtils.bd09toWgs84(startLng, startLat);
            double[] end = GPSConverterUtils.bd09toWgs84(endLng, endLat);
            return getDistance(start[0], start[1], end[0], end[1]);
        }
        return getDistance(startLng, startLat, endLng, endLat);
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
        result.put(CommonConstant.LONGITUDE, CommonUtil.round(lng, 6).doubleValue());
        result.put(CommonConstant.LATITUDE, CommonUtil.round(lat, 6).doubleValue());
        return result;
    }

    /**
     * 带入坐标系计算距离角度外的一点
     * <p>
     * 根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴
     *
     * @param angle                角度，从正北顺时针方向开始计算
     * @param startLng             起始点经度
     * @param startLat             起始点纬度
     * @param distance             距离，单位m
     * @param coordinateSystemEnum 坐标系
     * @return 经纬度map
     */
    public Map<String, Double> calLocationByDistanceAndLocationAndDirection(double angle, double startLng, double startLat, double distance, CoordinateSystemEnum coordinateSystemEnum) {
        // 火星坐标系转化
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.GCJ02)) {
            double[] trans = GPSConverterUtils.gcj02toWgs84(startLng, startLat);
            return calLocationByDistanceAndLocationAndDirection(angle, trans[0], trans[1], distance);
        }
        // 百度坐标系转换
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.BD09)) {
            double[] trans = GPSConverterUtils.bd09toWgs84(startLng, startLat);
            return calLocationByDistanceAndLocationAndDirection(angle, trans[0], trans[1], distance);
        }
        return calLocationByDistanceAndLocationAndDirection(angle, startLng, startLat, distance);
    }

    /**
     * 判断一个点是否在圆形区域内
     *
     * @param lat1   圆心纬度
     * @param lng1   圆心经度
     * @param lat2   坐标纬度
     * @param lng2   坐标经度
     * @param radius 需要计算的半径d
     * @return true 在范围内 false 不在范围内
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, double radius) {
        return getDistance(lng1, lat1, lng2, lat2) <= radius;
    }

    /**
     * 判断一个点是否在圆形区域内 带入坐标系
     *
     * @param lat1                 圆心纬度
     * @param lng1                 圆心经度
     * @param lat2                 坐标纬度
     * @param lng2                 坐标经度
     * @param radius               需要计算的半径
     * @param coordinateSystemEnum 坐标系
     * @return true 在范围内 false 不在范围内
     */
    public static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, double radius, CoordinateSystemEnum coordinateSystemEnum) {
        return getDistance(lng1, lat1, lng2, lat2, coordinateSystemEnum) <= radius;
    }

    /**
     * 判断是否在多边形区域内
     *
     * @param pointLon 要判断的点的纵坐标
     * @param pointLat 要判断的点的横坐标
     * @param points   经纬度json数组 "[{\"x\":120.61123416,\"y\":31.32889074,\"z\":137.05},{\"x\":120.61312695,\"y\":31.31892631,\"z\":128.61},{\"x\":120.61455616,\"y\":31.30808702,\"z\":43.66},{\"x\":120.62127327,\"y\":31.30899876,\"z\":62.21},{\"x\":120.63003506,\"y\":31.31057071,\"z\":29.43},{\"x\":120.63726235,\"y\":31.31203339,\"z\":92.90},{\"x\":120.64536616,\"y\":31.31334188,\"z\":78.36},{\"x\":120.64402082,\"y\":31.31947999,\"z\":13.19},{\"x\":120.64136126,\"y\":31.32757908,\"z\":87.36},{\"x\":120.63689776,\"y\":31.33287239,\"z\":60.62},{\"x\":120.63502091,\"y\":31.33742080,\"z\":114.21},{\"x\":120.63071787,\"y\":31.33793104,\"z\":32.99},{\"x\":120.62952446,\"y\":31.34483170,\"z\":164.79},{\"x\":120.62710968,\"y\":31.34801804,\"z\":164.15},{\"x\":120.62731359,\"y\":31.34823458,\"z\":189.53},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62665860,\"y\":31.34861797,\"z\":155.41},{\"x\":120.61706620,\"y\":31.34846463,\"z\":200.05},{\"x\":120.61854348,\"y\":31.34267516,\"z\":138.68},{\"x\":120.62111689,\"y\":31.33313042,\"z\":154.61}]"
     * @return true 在范围内 false 不在范围内
     */
    public static boolean isInPolygon(double pointLon, double pointLat, List<Point3D> points) {
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<>();
        double polygonPointX, polygonPointY;
        int len = points.size();
        for (int i = 0; i < len; i++) {
            polygonPointX = points.get(i).getX();
            polygonPointY = points.get(i).getY();
            Point2D.Double polygonPoint = new Point2D.Double(polygonPointX, polygonPointY);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }

    /**
     * 判断是否在多边形区域内
     *
     * @param pointLon             要判断的点的纵坐标
     * @param pointLat             要判断的点的横坐标
     * @param points               经纬度json数组 "[{\"x\":120.61123416,\"y\":31.32889074,\"z\":137.05},{\"x\":120.61312695,\"y\":31.31892631,\"z\":128.61},{\"x\":120.61455616,\"y\":31.30808702,\"z\":43.66},{\"x\":120.62127327,\"y\":31.30899876,\"z\":62.21},{\"x\":120.63003506,\"y\":31.31057071,\"z\":29.43},{\"x\":120.63726235,\"y\":31.31203339,\"z\":92.90},{\"x\":120.64536616,\"y\":31.31334188,\"z\":78.36},{\"x\":120.64402082,\"y\":31.31947999,\"z\":13.19},{\"x\":120.64136126,\"y\":31.32757908,\"z\":87.36},{\"x\":120.63689776,\"y\":31.33287239,\"z\":60.62},{\"x\":120.63502091,\"y\":31.33742080,\"z\":114.21},{\"x\":120.63071787,\"y\":31.33793104,\"z\":32.99},{\"x\":120.62952446,\"y\":31.34483170,\"z\":164.79},{\"x\":120.62710968,\"y\":31.34801804,\"z\":164.15},{\"x\":120.62731359,\"y\":31.34823458,\"z\":189.53},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62665860,\"y\":31.34861797,\"z\":155.41},{\"x\":120.61706620,\"y\":31.34846463,\"z\":200.05},{\"x\":120.61854348,\"y\":31.34267516,\"z\":138.68},{\"x\":120.62111689,\"y\":31.33313042,\"z\":154.61}]"
     * @param coordinateSystemEnum 坐标系
     * @return true 在范围内 false 不在范围内
     */
    public static boolean isInPolygon(double pointLon, double pointLat, List<Point3D> points, CoordinateSystemEnum coordinateSystemEnum) {
        // 火星坐标系转化
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.GCJ02)) {
            double[] trans = GPSConverterUtils.gcj02toWgs84(pointLon, pointLat);
            pointLon = trans[0];
            pointLat = trans[1];
        }
        // 百度坐标系转换
        if (coordinateSystemEnum.equals(CoordinateSystemEnum.BD09)) {
            double[] trans = GPSConverterUtils.bd09toWgs84(pointLon, pointLat);
            pointLon = trans[0];
            pointLat = trans[1];
        }
        // 将要判断的横纵坐标组成一个点
        Point2D.Double point = new Point.Double(pointLon, pointLat);
        // 将区域各顶点的横纵坐标放到一个点集合里面
        List<Point2D.Double> pointList = new ArrayList<>();
        double polygonPointX, polygonPointY;
        int len = points.size();
        for (int i = 0; i < len; i++) {
            polygonPointX = points.get(i).getX();
            polygonPointY = points.get(i).getY();
            // 火星坐标系转化
            if (coordinateSystemEnum.equals(CoordinateSystemEnum.GCJ02)) {
                double[] trans = GPSConverterUtils.gcj02toWgs84(polygonPointX, polygonPointY);
                polygonPointX = trans[0];
                polygonPointY = trans[1];
            }
            // 百度坐标系转换
            if (coordinateSystemEnum.equals(CoordinateSystemEnum.BD09)) {
                double[] trans = GPSConverterUtils.bd09toWgs84(polygonPointX, polygonPointY);
                polygonPointX = trans[0];
                polygonPointY = trans[1];
            }
            Point2D.Double polygonPoint = new Point2D.Double(polygonPointX, polygonPointY);
            pointList.add(polygonPoint);
        }
        return check(point, pointList);
    }

    /**
     * 一个点是否在多边形内
     *
     * @param point   要判断的点的横纵坐标
     * @param polygon 组成的顶点坐标集合
     * @return true 在范围内 false 不在范围内
     */
    private static boolean check(Point2D.Double point, List<Point2D.Double> polygon) {
        GeneralPath penalPath = new GeneralPath();
        Point2D.Double first = polygon.get(0);
        // 通过移动到指定坐标（以双精度指定），将一个点添加到路径中
        penalPath.moveTo(first.x, first.y);
        polygon.remove(0);
        for (Point2D.Double d : polygon) {
            // 通过绘制一条从当前坐标到新指定坐标（以双精度指定）的直线，将一个点添加到路径中。
            penalPath.lineTo(d.x, d.y);
        }
        // 将几何多边形封闭
        penalPath.lineTo(first.x, first.y);
        penalPath.closePath();
        // 测试指定的 Point2D 是否在 Shape 的边界内。
        return penalPath.contains(point);
    }

    /**
     * 计算是否在扇形区域内
     *
     * @param startLng 起始经度
     * @param startLat 其实纬度
     * @param angel    需要计算的角度
     * @param diffuse  计算角度向两边扩散度数
     * @param checkLng 需要校验的经度
     * @param checkLat 需要校验的纬度
     * @return true 在扇形范围内 false 不在扇形范围内
     */
    public static boolean isInSector(double startLng, double startLat, double angel, double diffuse, double checkLng, double checkLat) {
        // 计算角度范围
        double minAngel = angel - diffuse;
        double maxAngel = angel + diffuse;
        // 计算斜边
        double st = Math.atan2(checkLat - startLat, checkLng - startLng);
        return IntervalUtil.checkInAllCloseInterval(minAngel, maxAngel, (st - 2 * Math.PI) * 180 / Math.PI) || IntervalUtil.checkInAllCloseInterval(minAngel, maxAngel, st * 180 / Math.PI) || IntervalUtil.checkInAllCloseInterval(minAngel, maxAngel, (st + 2 * Math.PI) * 180 / Math.PI);
    }

    /**
     * 计算是否在扇形区域内
     *
     * @param startLng 起始经度
     * @param startLat 其实纬度
     * @param angel    需要计算的角度
     * @param diffuse  计算角度向两边扩散度数
     * @param checkLng 需要校验的经度
     * @param checkLat 需要校验的纬度
     * @param distance 计算距离
     * @return true 在扇形范围内 false 不在扇形范围内
     */
    public static boolean isInSector(double startLng, double startLat, double angel, double diffuse, double checkLng, double checkLat, Double distance) {
        if (!isInCircle(startLng, startLat, checkLng, checkLat, distance)) {
            return false;
        }
        return isInSector(startLng, startLat, angel, diffuse, checkLng, checkLat);
    }

    /**
     * 根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴
     *
     * @param angle，从正北顺时针方向开始计算
     * @param point3D            计算位置的
     * @param distance           距离，单位m
     * @return 经纬度map
     */
    public Point3D calLocationByDistanceAndLocationAndDirection(double angle, Point3D point3D, double distance) {
        //将距离转换成经度的计算公式
        double sigma = distance / WGS84_EARTH_RADIUS;
        // 转换为radian，否则结果会不正确
        angle = Math.toRadians(angle);
        point3D.setX(Math.toRadians(point3D.getX()));
        point3D.setY(Math.toRadians(point3D.getY()));
        double lat = Math.asin(Math.sin(point3D.getY()) * Math.cos(sigma) + Math.cos(point3D.getY()) * Math.sin(sigma) * Math.cos(angle));
        double lng = point3D.getX() + Math.atan2(Math.sin(angle) * Math.sin(sigma) * Math.cos(point3D.getY()), Math.cos(sigma) - Math.sin(point3D.getY()) * Math.sin(lat));
        // 转为正常的10进制经纬度
        lng = Math.toDegrees(lng);
        lat = Math.toDegrees(lat);
        return new Point3D(CommonUtil.round(lng, 6).doubleValue(), CommonUtil.round(lat, 6).doubleValue(), point3D.getZ());
    }

    /**
     * 求一点相对于另一点旋转一定角度后的坐标
     *
     * @param cenerPoint 中心点
     * @param point      待旋转的点
     * @param legel      旋转角度
     * @return 坐标数组
     */
    public static Double[] route(Double[] cenerPoint, Double[] point, Double legel) {
        final double cos = Math.cos(Math.PI * (360 - legel - 90) / 180);
        final double sin = Math.sin(Math.PI * (360 - legel - 90) / 180);
        Double x = (point[0] - cenerPoint[0]) * cos - (point[1] - cenerPoint[1]) * sin + cenerPoint[0];
        Double y = (point[1] - cenerPoint[1]) * cos + (point[0] - cenerPoint[0]) * sin + cenerPoint[1];
        Double[] result = new Double[]{x, y};
        return result;
    }

    /**
     * 偏移
     * 依照墨卡托坐标计算
     *
     * @param pointMercator 点位
     * @return 墨卡托坐标数组
     */
    public static Double[] deviation(Double[] pointMercator) {
        // 地图中心点，墨卡托坐标
        Double[] olxy = new Double[]{13053846.692025987, 3837666.68453955};
        double x = olxy[0] + Math.ceil((pointMercator[0] - olxy[0]) / 20) * 20;
        double y = olxy[1] + Math.ceil((pointMercator[1] - olxy[1]) / 20) * 20;
        return new Double[]{x, y};
    }
}
