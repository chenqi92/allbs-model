package cn.allbs.utils.JBF293K.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 EleFireStatusEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 16:27
 */
@Getter
public enum EleFireStatusEnum {

    /**
     * 报警
     */
    ALARM("报警", (short) 0x1),

    /**
     * 故障
     */
    FAULT("故障", (short) 0x2),

    /**
     * 故障撤销
     */
    FAILURE_UNDO("故障撤销", (short) 0x3),

    /**
     * 脱扣动作(自动)
     */
    THE_TRIP_ACTION_IS_AUTOMATIC("脱扣动作(自动)", (short) 0x4),

    /**
     * 脱扣停止(自动)
     */
    TRIP_STOP_AUTOMATICALLY("脱扣停止(自动)", (short) 0x5),

    /**
     * 脱扣动作(手动)
     */
    TRIP_ACTION_MANUAL("脱扣动作(手动)", (short) 0x6),

    /**
     * 脱扣停止(手动)
     */
    TRIPPING_STOPS_MANUALLY("脱扣停止(手动)", (short) 0x7),

    /**
     * 屏蔽
     */
    SHIELD("屏蔽", (short) 0x8),

    /**
     * 屏蔽撤销
     */
    BLOCKING_REVOCATION("屏蔽撤销", (short) 0x9),
    ;

    public static final Map<Short, String> ELE_FIRE_STATUS_MAP = new HashMap<>(9);

    static {
        EleFireStatusEnum[] efs = EleFireStatusEnum.values();
        for (EleFireStatusEnum ef : efs) {
            ELE_FIRE_STATUS_MAP.put(ef.type, ef.desc);
        }
    }

    private final String desc;

    private final Short type;

    EleFireStatusEnum(String desc, Short type) {
        this.desc = desc;
        this.type = type;
    }
}
