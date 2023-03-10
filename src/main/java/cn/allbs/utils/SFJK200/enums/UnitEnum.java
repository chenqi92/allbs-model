package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 类 UnitEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/3/8 13:16
 */
@Getter
public enum UnitEnum {

    TOXIC_GASES_LARGE_RANGE("有毒气体,大量程浓度无小数点", "ppm", 0, 1),

    COMBUSTIBLE_GAS("可燃气体", "%LEL", 1),

    OXYGEN_NITROGEN("其他类型气体", "%V/V", 2),

    TOXIC_GAS_SMALL_RANGE("有毒气体,小量程浓度有小数点", "ppm", 3);

    private final String desc;

    private final String unit;

    private final Integer type;

    /**
     * 计算时需要除以的比例，单位为0的不是
     */
    private final int rate;

    public static final Map<Integer, UnitEnum> UNIT_ENUM_MAP = new HashMap<>(4);

    static {
        UnitEnum[] unitEnums = UnitEnum.values();
        for (UnitEnum unit : unitEnums) {
            UNIT_ENUM_MAP.put(unit.getType(), unit);
        }
    }

    UnitEnum(String desc, String unit, Integer type) {
        this.desc = desc;
        this.unit = unit;
        this.type = type;
        this.rate = 10;
    }

    UnitEnum(String desc, String unit, Integer type, int rate) {
        this.desc = desc;
        this.unit = unit;
        this.type = type;
        this.rate = rate;
    }


}
