package cn.allbs.utils.JBF293K.enums;

import cn.allbs.constant.StringPoolConstant;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 EleFireTypeEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 16:26
 */
@Getter
public enum EleFireTypeEnum {

    /**
     * 全部探测器
     */
    ALL_DETECTORS("全部探测器", (short) 0x1),

    /**
     * 剩余电流探测器
     */
    RESIDUAL_CURRENT_DETECTOR("剩余电流探测器", (short) 0x2, 1, "mA"),

    /**
     * 温度探测器
     */
    TEMPERATURE_DETECTOR("温度探测器", (short) 0x3, 1, "摄氏度", true),

    /**
     * 故障电弧探测器
     */
    FAULTY_ARC_DETECTOR("故障电弧探测器", (short) 0x4),

    /**
     * 过电流探测器
     */
    OVER_CURRENT_DETECTOR("过电流探测器", (short) 0x5, 2, "A"),

    /**
     * 脱扣继电器
     */
    TRIP_RELAYS("脱扣继电器", (short) 0x6),

    /**
     * 预留
     */
    OBLIGATE("预留", (short) 0x7),

    ;

    public static final Map<Short, EleFireTypeEnum> ELE_FIRE_TYPE_MAP = new HashMap<>(7);

    static {
        EleFireTypeEnum[] efs = EleFireTypeEnum.values();
        for (EleFireTypeEnum ef : efs) {
            ELE_FIRE_TYPE_MAP.put(ef.type, ef);
        }
    }

    private final String desc;

    private final Short type;

    /**
     * 小数位数, 1位系统为10,2位系数为100
     */
    private final Integer factor;

    private final String unit;

    /**
     * 是否包含负数
     */
    private final Boolean minus;

    EleFireTypeEnum(String desc, Short type) {
        this.desc = desc;
        this.type = type;
        this.factor = 0;
        this.unit = StringPoolConstant.EMPTY;
        this.minus = false;
    }

    EleFireTypeEnum(String desc, Short type, Integer factor, String unit) {
        this.desc = desc;
        this.type = type;
        this.factor = factor;
        this.unit = unit;
        this.minus = false;
    }

    EleFireTypeEnum(String desc, Short type, Integer factor, String unit, Boolean minus) {
        this.desc = desc;
        this.type = type;
        this.factor = factor;
        this.unit = unit;
        this.minus = minus;
    }
}
