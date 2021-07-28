package cn.allbs.model;

import lombok.Data;

/**
 * 地球经纬度
 *
 * @author ChenQi
 */
@Data
public class EarthPoint2D {

    /**
     * 经度
     */
    private Double lng;

    /**
     * 纬度
     */
    private Double lat;

    /**
     * 构造方法
     *
     * @param lng 经度
     * @param lat 维度
     */
    public EarthPoint2D(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }
}
