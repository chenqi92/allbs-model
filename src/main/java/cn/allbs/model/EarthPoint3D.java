package cn.allbs.model;

import lombok.Data;

/**
 * 空间点坐标 + 偏移距离信息
 *
 * @author ChenQi
 */
@Data
public class EarthPoint3D {

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 高度
     */
    private Double height;

    /**
     * x轴偏移距离
     */
    private Double x;

    /**
     * y轴偏移距离
     */
    private Double y;

    /**
     * z轴偏移距离
     */
    private Double z;

    /**
     * 构造器
     *
     * @param point3D 空间点对象
     * @param x       x轴平移距离
     * @param y       y轴平移距离
     * @param z       z轴平移距离
     */
    public EarthPoint3D(Point3D point3D, double x, double y, double z) {
        this.lng = point3D.getX();
        this.lat = point3D.getY();
        this.height = point3D.getZ();
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 构造器
     *
     * @param x x轴平移距离
     * @param y y轴平移距离
     * @param z z轴平移距离
     */
    public EarthPoint3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
