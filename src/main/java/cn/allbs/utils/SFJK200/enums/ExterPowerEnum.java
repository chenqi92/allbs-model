package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 ExterPowerEnum
 * </p>
 * 外控电源状态定义
 *
 * @author ChenQi
 * @since 2023/3/8 13:26
 */
@Getter
public enum ExterPowerEnum {

    NORMAL_NORMAL("主电正常、备电正常", 0x30, 0x70),

    UNDERVOLTAGE_NORMAL("主电欠压、备电正常", 0x20, 0x60),

    NORMAL_OPEN("主电正常、备电开路", 0x33, 0x73),

    UNDERVOLTAGE_OPEN_CIRCUIT("主电欠压、备电开路", 0x23, 0x63),

    NORMAL_SHORT_CIRCUIT("主电正常、备电短路", 0x31, 0x71),

    UNDERVOLTAGE_SHORT_CIRCUIT("主电欠压、备电短路", 0x21, 0x61),

    OFF_NORMAL("主电关闭、备电正常", 0x10, 0x50),

    OFF_UNDERVOLTAGE("主电关闭、备电欠压", 0x11, 0x51),
    ;

    private final String desc;

    private final Integer normal;

    private final Integer opening;

    public static final Map<Integer, String> EXTRA_POWER_MAP = new HashMap<>(16);

    static {
        ExterPowerEnum[] exterPowerEnums = ExterPowerEnum.values();
        for (ExterPowerEnum extra : exterPowerEnums) {
            EXTRA_POWER_MAP.put(extra.normal, extra.getDesc());
            EXTRA_POWER_MAP.put(extra.opening, extra.getDesc());
        }
    }

    ExterPowerEnum(String desc, Integer normal, Integer opening) {
        this.desc = desc;
        this.normal = normal;
        this.opening = opening;
    }
}
