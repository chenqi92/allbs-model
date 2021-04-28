package cn.allbs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 水质等级划分
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum WaterLevelEnum {

    /**
     * 水质等级
     */
    I("I类", 1), II("II类", 2), III("III类", 3), IV("IV类", 4), V("V类", 5), VI("劣五类", 6);

    private final String description;

    private final Integer level;
}
