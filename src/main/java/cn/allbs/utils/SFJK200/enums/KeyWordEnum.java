package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

/**
 * 枚举 KeyWordEnum
 * </p>
 * 返回数据关键词枚举
 *
 * @author ChenQi
 * @since 2023/3/9 13:14
 */
@Getter
public enum KeyWordEnum {

    DATA("data", "数据信息"),

    /**
     * 回路
     */
    CIRCUIT("circuit", "回路"),

    /**
     * 点位
     */
    POINT("point", "点数"),

    GAS_TYPE("gas_type", "点位属性"),

    GAS_TYPE_PRE("pre_gas_type", "点位属性原始值"),

    GAS_UNIT("gas_unit", "气体单位"),

    GAS_UNIT_PRE("pre_gas_unit", "气体单位原始值"),

    GAS_NUM("gas_num", "气体监测值"),

    DETECTOR_STATUS("detector_status", "探测器状态"),

    DETECTOR_STATUS_PRE("pre_detector_status", "探测器状态原始值"),

    DETECTOR_NUM("detector_num", "探测器浓度值"),

    EXTRA_POWER("extra_power", "外控电源状态"),

    EXTRA_POWER_PRE("pre_extra_power", "外控电源状态原始值"),

    ADDRESS("address", "地址"),

    FUNCTION("function", "功能"),

    CONTROLLER_STATUS("controller_status", "控制器状态"),

    CONTROLLER_STATUS_PRE("pre_controller_status", "控制器状态原始值"),
    ;

    private final String key;

    private final String desc;

    KeyWordEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
