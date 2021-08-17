package cn.allbs.utils;

import cn.allbs.model.EarthPoint3D;
import cn.allbs.model.Point3D;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ArrayUtil;
import lombok.experimental.UtilityClass;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 空间几何工具类
 *
 * @author ChenQi
 */
@UtilityClass
public class SpaceGeometryUtil {

    /**
     * 计算立方体顶点坐标
     *
     * @param point3D  立方体中心点的坐标
     * @param distance 立方体边长/2
     * @return 立方体8个顶点坐标
     */
    public Set<Point3D> cubePeak(Point3D point3D, double distance) {
        Set<Point3D> points = new HashSet<>();
        points.add(new Point3D(point3D.getX() - distance, point3D.getY() - distance, point3D.getZ() + distance));
        points.add(new Point3D(point3D.getX() - distance, point3D.getY() - distance, point3D.getZ() - distance));
        points.add(new Point3D(point3D.getX() + distance, point3D.getY() - distance, point3D.getZ() - distance));
        points.add(new Point3D(point3D.getX() + distance, point3D.getY() - distance, point3D.getZ() + distance));
        points.add(new Point3D(point3D.getX() + distance, point3D.getY() + distance, point3D.getZ() + distance));
        points.add(new Point3D(point3D.getX() + distance, point3D.getY() + distance, point3D.getZ() - distance));
        points.add(new Point3D(point3D.getX() - distance, point3D.getY() + distance, point3D.getZ() - distance));
        points.add(new Point3D(point3D.getX() - distance, point3D.getY() + distance, point3D.getZ() + distance));
        return points;
    }

    /**
     * 计算立方体6个面
     *
     * @param point3D  立方体中心点坐标
     * @param distance 立方体的边长/2
     * @return 立方体的8个面
     */
    public Set<Set<Point3D>> cubeFace(Point3D point3D, double distance) {
        Set<Set<Point3D>> faces = new HashSet<>();
        Set<Point3D> points = cubePeak(point3D, distance);
        Point3D[] pointsArray = ArrayUtil.toArray(points, Point3D.class);
        faces.add(CollUtil.newHashSet(pointsArray[0], pointsArray[0], pointsArray[0], pointsArray[0]));
        faces.add(CollUtil.newHashSet(pointsArray[3], pointsArray[2], pointsArray[5], pointsArray[4]));
        faces.add(CollUtil.newHashSet(pointsArray[4], pointsArray[5], pointsArray[6], pointsArray[7]));
        faces.add(CollUtil.newHashSet(pointsArray[7], pointsArray[6], pointsArray[1], pointsArray[0]));
        faces.add(CollUtil.newHashSet(pointsArray[7], pointsArray[0], pointsArray[3], pointsArray[4]));
        faces.add(CollUtil.newHashSet(pointsArray[1], pointsArray[6], pointsArray[5], pointsArray[2]));
        return faces;
    }

    /**
     * 计算空间立方体延伸距离保证数据充实
     *
     * @param point3D   立方体中心点坐标
     * @param distance  立方体的边长/2
     * @param extendDis 延伸的距离
     * @return 空间立方体延伸距离
     */
    public Set<Point3D> batchPeak(Point3D point3D, double distance, double extendDis) {
        Set<Point3D> centerPoints = cubePeak(point3D, distance);
        Set<Point3D> batchPoints = new HashSet<>(centerPoints);
        centerPoints.forEach(point -> batchPoints.addAll(cubePeak(point, extendDis)));
        return batchPoints;
    }

    /**
     * 计算地球中立方体顶点坐标
     *
     * @param point3D  立方体中心点的坐标
     * @param distance 立方体边长/2
     * @return 立方体8个顶点坐标
     */
    public Set<Point3D> earthCubePeak(Point3D point3D, double distance) {
        Set<Point3D> points = new HashSet<>();
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(225, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(225, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(135, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(135, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(45, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(45, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(315, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance));
        points.add(LngLatUtil.calLocationByDistanceAndLocationAndDirection(315, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance));
        return points;
    }

    /**
     * 计算地球中立方体顶点坐标
     *
     * @param point3D   立方体中心点的坐标
     * @param distance  立方体边长/2
     * @param extendDis 辅助计算空间点的距离
     * @return 地球中立方体8个顶点坐标
     */
    public Set<Point3D> earthBatchPeak(Point3D point3D, double distance, double extendDis) {
        Set<Point3D> centerPoints = earthCubePeak(point3D, distance);
        Set<Point3D> batchPoints = new HashSet<>(centerPoints);
        centerPoints.forEach(point -> batchPoints.addAll(earthCubePeak(point, extendDis)));
        return batchPoints;
    }

    /**
     * 包含风向计算地球中立方体顶点坐标
     *
     * @param point3D  立方体中心点的坐标
     * @param distance 立方体边长/2
     * @param angle    风向角度
     * @return 立方体8个顶点坐标
     */
    public Set<EarthPoint3D> earthCubePeakDetail(Point3D point3D, double distance, double angle) {
        Set<EarthPoint3D> points = new HashSet<>();
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 225, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance), -distance, -distance, distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 225, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance), -distance, -distance, -distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 135, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance), distance, -distance, -distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 135, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance), distance, -distance, distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 45, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance), distance, distance, distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 45, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance), distance, distance, -distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 315, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() - distance), Math.sqrt(2) * distance), -distance, distance, -distance));
        points.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + 315, new Point3D(point3D.getX(), point3D.getY(), point3D.getZ() + distance), Math.sqrt(2) * distance), -distance, distance, distance));
        return points;
    }

    /**
     * 计算地球中立方体顶点坐标
     *
     * @param point3D   立方体中心点的坐标
     * @param distance  立方体边长/2
     * @param extendDis 辅助距离使数据充实
     * @param angle     风向角度
     * @return 地球中立方体8个顶点坐标
     */
    public Set<EarthPoint3D> earthBatchPeakDetail(Point3D point3D, double distance, double extendDis, double angle) {
        Set<EarthPoint3D> centerPoints = earthCubePeakDetail(point3D, distance, angle);
        Set<EarthPoint3D> batchPoints = new HashSet<>(centerPoints);
        centerPoints.forEach(point -> batchPoints.addAll(earthCubePeakDetail(new Point3D(point.getLng(), point.getLat(), point.getHeight()), extendDis, angle)));
        return batchPoints;
    }

    /**
     * 计算地球中立方体顶点坐标
     *
     * @param point3D   立方体中心点的坐标
     * @param distance  立方体边长/2
     * @param extendDis 辅助距离使数据充实
     * @return 地球中立方体8个顶点坐标
     */
    public Set<EarthPoint3D> earthBatchPeakDetail(Point3D point3D, double distance, double extendDis) {
        return earthBatchPeakDetail(point3D, distance, extendDis, 0);
    }

    /**
     * 包含风向计算地球中立方体顶点坐标（顺风向）
     *
     * @param point3D  立方体中心点的坐标
     * @param distance 立方体边长/2
     * @param angle    风向角度
     * @param step     平均风的步长
     * @return 立方体多个顶点坐标
     */
    public Set<EarthPoint3D> EarthBatchPeakDetailToWd(Point3D point3D, double distance, double angle, double step) {
        Set<EarthPoint3D> batchPoints = totalEarthBatchPeakDetailWithoutWd(point3D, distance, angle, step);
        batchPoints = batchPoints.stream().filter(a -> a.getX() > 0).collect(Collectors.toSet());
        return batchPoints;
    }

    /**
     * 不包含风向计算地球中立方体顶点坐标
     *
     * @param point3D  立方体中心点的坐标
     * @param distance 立方体边长/2
     * @param angle    风向角度
     * @param step     平均风的步长
     * @return 立方体多个顶点坐标
     */
    public Set<EarthPoint3D> totalEarthBatchPeakDetailWithoutWd(Point3D point3D, double distance, double angle, double step) {
        Set<EarthPoint3D> batchPoints = new HashSet<>();
        double h = point3D.getZ();
        // 平移距离/步长向上取整
        Set<Double> xRange = CollUtil.newHashSet(distance, -distance);
        Set<Double> yRange = CollUtil.newHashSet(distance, -distance);
        Set<Double> zRange = CollUtil.newHashSet(point3D.getZ());
        int section = Convert.toInt(Math.ceil(distance / step));
        for (int index = 0; index < section; index++) {
            xRange.add(index * step);
            xRange.add(-index * step);
            yRange.add(index * step);
            yRange.add(-index * step);
            zRange.add(h + index * step);
            zRange.add(h - index * step);
        }
        for (Double aDouble : xRange) {
            for (Double value : yRange) {
                for (Double item : zRange) {
                    double x = aDouble;
                    double y = value;
                    double z = item;
                    double dis = Math.sqrt(x * x + y * y);
                    double ag = Math.toDegrees(Math.atan2(Math.abs(y), x));
                    if (y > 0) {
                        batchPoints.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle - ag,
                                new Point3D(point3D.getX(), point3D.getY(), z), dis),
                                x, y, z));
                    } else {
                        batchPoints.add(new EarthPoint3D(LngLatUtil.calLocationByDistanceAndLocationAndDirection(angle + ag,
                                new Point3D(point3D.getX(), point3D.getY(), z), dis),
                                x, y, z));
                    }
                }
            }
        }
        return batchPoints;
    }

    /**
     * 计算一个中心点之外所有点位
     *
     * @param xStep  x方向步长
     * @param yStep  y方向步长
     * @param zStep  z方向步长
     * @param xLimit x向外延申得距离
     * @param yLimit y向外延申得距离
     * @param zLimit z向外延申得距离
     * @param xCount 是否计算x负方向
     * @param yCount 是否计算y负方向
     * @param zCount 是否计算z负方向
     * @return 中心点外所有点位列表
     */
    public Set<Point3D> takeAllPoints(double xStep, double yStep, double zStep, double xLimit, double yLimit, double zLimit, boolean xCount, boolean yCount, boolean zCount) {
        if (xStep == 0 || yStep == 0 || zStep == 0) {
            return new HashSet<>();
        }
        Point3D point3D = new Point3D(0, 0, 0);
        Set<Point3D> point3DS = new HashSet<>();
        Set<Double> xSet = new HashSet<>();
        Set<Double> ySet = new HashSet<>();
        Set<Double> zSet = new HashSet<>();
        for (double x = 0; x <= xLimit; x += xStep) {
            xSet.add(x);
            if (xCount) {
                xSet.add(-x);
            }
        }
        for (double y = 0; y <= yLimit; y += yStep) {
            ySet.add(y);
            if (yCount) {
                ySet.add(-y);
            }
        }
        for (double z = 0; z <= zLimit; z += zStep) {
            zSet.add(z);
            if (zCount) {
                zSet.add(-z);
            }
        }
        zSet.forEach(z -> ySet.forEach(y -> xSet.forEach(x -> point3DS.add(point3D.add(x, y, z)))));
        return point3DS;
    }
}
