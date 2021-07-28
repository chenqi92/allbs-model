package cn.allbs.utils;

import cn.allbs.model.EarthPoint2D;
import cn.allbs.model.Point2D;
import lombok.experimental.UtilityClass;


/**
 * WGS84坐标系与墨卡托坐标系转换
 *
 * @author ChenQi
 */
@UtilityClass
public class WGS84MercatorToLngLatUtil {

    /**
     * 墨卡托转WGS84
     *
     * @param point2D 墨卡托坐标
     * @return WGS84坐标
     */
    public EarthPoint2D mercatorToWgs84(Point2D point2D) {
        double x = point2D.getX() / 20037508.34 * 180;
        double y = point2D.getY() / 20037508.34 * 180;
        y = 180 / Math.PI * (2 * Math.atan(Math.exp(y * Math.PI / 180)) - Math.PI / 2);
        return new EarthPoint2D(x, y);
    }

    /**
     * WGS84转墨卡托坐标
     *
     * @param earthPoint2D WGS84坐标
     * @return 墨卡托坐标
     */
    public Point2D wgs84ToMercator(EarthPoint2D earthPoint2D) {
        double x = earthPoint2D.getLng() * 20037508.34 / 180;
        double y = Math.log(Math.tan((90 + earthPoint2D.getLat()) * Math.PI / 360)) / (Math.PI / 180);
        y = y * 20037508.34 / 180;
        return new Point2D(x, y);
    }
}
