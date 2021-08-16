package cn.allbs.model;

import lombok.Data;

/**
 * 空间点中的经纬度高度和该位置的数值信息
 *
 * @author ChenQi
 */
@Data
public class SpacePoint {

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
     * 浓度、辐射值等数值信息
     */
    private Double c;

    /**
     * 浓度对应等级
     */
    private Integer level;

    /**
     * 空间点坐标及其位置浓度
     *
     * @param point3D 空间坐标对象
     * @param c       浓度、辐射度等数值信息
     */
    public SpacePoint(Point3D point3D, double c) {
        this.lng = point3D.getX();
        this.lat = point3D.getY();
        this.height = point3D.getZ();
        this.c = c;
    }

    /**
     * 空间点坐标及其位置浓度
     *
     * @param earthPoint3D 空间坐标对象
     * @param c            浓度、辐射度等数值信息
     */
    public SpacePoint(EarthPoint3D earthPoint3D, double c) {
        this.lng = earthPoint3D.getLng();
        this.lat = earthPoint3D.getLat();
        this.height = earthPoint3D.getHeight();
        this.c = c;
    }
}
