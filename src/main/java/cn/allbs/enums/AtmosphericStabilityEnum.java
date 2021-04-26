package cn.allbs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 大气稳定度
 *
 * @author ChenQi
 * @date 2021/4/21
 */
@Getter
@AllArgsConstructor
public enum AtmosphericStabilityEnum {

    /**
     * 大气稳定度
     */
    LEVEL1(3), LEVEL2(2), LEVEL3(1), LEVEL4(0), LEVEL5(-1), LEVEL6(-2);

    private final Integer level;
}
