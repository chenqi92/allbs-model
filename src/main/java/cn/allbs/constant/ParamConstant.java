package cn.allbs.constant;

import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * 常量
 *
 * @author ChenQi
 */
public interface ParamConstant {

    /**
     * 1000
     */
    Integer THOUSAND = 1000;

    /**
     * 2000
     */
    Integer TWO_THOUSAND = 2000;

    /**
     * 300
     */
    Integer THREE_HUNDRED = 300;

    /**
     * 500
     */
    Integer FIVE_HUNDRED = 500;

    /**
     * 10000
     */
    Integer TEN_THOUSAND = 10000;

    /**
     * 空气质量分指数
     */
    List<Integer> I_AQI = CollUtil.list(true, 0, 50, 100, 150, 200, 300, 400, 500);

    /**
     * 首要污染物最小标准 （AQI 大于此数，IAQI最大的即为首要污染物 可以为多项）
     */
    Integer PRIMARY_POLLUTANT_MIN_LIMIT = 50;

    /**
     * 超标污染物最小标准(IAQI 大于此数的即为超标污染物)
     */
    Integer EXCESSIVE_POLLUTANT_MIN_LIMIT = 100;
}
