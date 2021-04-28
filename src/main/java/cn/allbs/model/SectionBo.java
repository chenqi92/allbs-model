package cn.allbs.model;

import lombok.Data;

/**
 * 数值区间
 *
 * @author ChenQi
 */
@Data
public class SectionBo {

    /**
     * 最小值
     */
    private Double min;

    /**
     * 最大值
     */
    private Double max;

    public SectionBo(Double min, Double max) {
        this.min = min;
        this.max = max;
    }
}