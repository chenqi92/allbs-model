package cn.allbs.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 单位枚举
 *
 * @author ChenQi
 */
@Getter
@AllArgsConstructor
public enum UnitEnum {

    /**
     * 常用单位枚举
     */
    A_L("个/升", "a/l"),
    GRAM("克", "g"),
    KILOGRAM("千克", "kg"),
    KILOGRAM_PER_CUBIC_METER("千克/立方米", "kg/m³"),
    KILOPASCAL("千帕", "kPa"),
    L_S("升/秒", "L/s"),
    TONS_PER_SQUARE_KILOMETER_MONTHS("吨/平方千米•月", "t/km²•m"),
    ANN_FROM("安[培]", "A"),
    MR("帕", "Pa"),
    SQUARE_METERS("平方米", "m²"),
    MICROGRAMS_PER_LITER("微克/升", "μg/L"),
    A_MICRO_WEST_PER_CENTIMETER("微西[门子]/厘米", "us/cm"),
    C("摄氏度", "°C"),
    DIMENSIONLESS("无量纲", "无量纲"),
    MILLIVOLT_T("毫伏[特]", "mV"),
    MG("毫克", "mg"),
    MG_L("毫克/升", "mg/L"),
    MG_M3("毫克/立方米", "mg/m³"),
    CUBIC_METERS("立方米", "m³"),
    CUBIC_METERS_PER_HOUR("立方米/小时", "m³/h"),
    CUBIC_METERS_PER_SECOND("立方米/秒", "m³/s"),
    METER("米", "m"),
    M_S("米/秒", "m/s"),
    G_L("纳克/升", "ng/L"),
    NANOGRAMS_PER_CUBIC_METER("纳克/立方米", "ng/m³"),
    BECK_REAR_PER_LITER("贝可[勒尔]/升", "Bq"),
    REVOLUTIONS_PER_MINUTE("转/分钟", "r/min"),
    PER("%", "%"),
    COLOR("[色]度", "°"),
    ANGLE_DEGREE("[角]度", "°");

    private final String cUnit;

    private final String eUnit;

    public static final Map<String, String> UNIT_MAP = new HashMap<>(41);

    static {
        UnitEnum[] enums = UnitEnum.values();
        for (UnitEnum unit : enums) {
            UNIT_MAP.put(unit.getCUnit(), unit.getEUnit());
        }
    }

}
