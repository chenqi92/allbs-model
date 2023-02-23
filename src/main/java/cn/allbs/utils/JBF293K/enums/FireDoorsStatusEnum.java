package cn.allbs.utils.JBF293K.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 FireDoorsStatus
 * </p>
 * 防火门部件-状态
 *
 * @author ChenQi
 * @since 2023/2/21 16:14
 */
@Getter
public enum FireDoorsStatusEnum {

    /**
     * 故障
     */
    FAULT("故障", (short) 0x1),

    /**
     * 故障撤销
     */
    FAILURE_UNDO("故障撤销", (short) 0x2),

    /**
     * 延时关闭
     */
    DELAY_OFF("延时关闭", (short) 0x3),

    /**
     * 正在关闭（自动）
     */
    TURNING_OFF_AUTOMATIC("正在关闭（自动）", (short) 0x4),

    /**
     * 正在关闭消息撤销(自动)
     */
    TURNING_OFF_MESSAGE_REVOCATION_AUTOMATICALLY("正在关闭消息撤销(自动)", (short) 0x5),

    /**
     * 正在关闭(手动)
     */
    CLOSING_MANUAL("正在关闭(手动)", (short) 0x6),

    /**
     * 正在关闭消息撤销(手动)
     */
    CLOSING_MESSAGE_REVOCATION_MANUALLY("正在关闭消息撤销(手动)", (short) 0x7),

    /**
     * 成功关闭(由于启动)
     */
    SUCCESSFULLY_SHUT_DOWN_DUE_TO_STARTUP("成功关闭(由于启动)", (short) 0x8),
    /**
     * 成功关闭消息撤销
     */
    MESSAGE_REVOCATION_WAS_SUCCESSFULLY_TURNED_OFF("成功关闭消息撤销", (short) 0x9),

    /**
     * 屏蔽
     */
    SHIELD("屏蔽", (short) 0xA),

    /**
     * 屏蔽撤销
     */
    BLOCKING_REVOCATION("屏蔽撤销", (short) 0xB),

    ;

    public static final Map<Short, String> DOOR_STATUS = new HashMap<>(12);

    static {
        FireDoorsStatusEnum[] statusEnums = FireDoorsStatusEnum.values();
        for (FireDoorsStatusEnum s : statusEnums) {
            DOOR_STATUS.put(s.type, s.desc);
        }
    }


    private final String desc;

    private final Short type;

    FireDoorsStatusEnum(String desc, Short type) {
        this.desc = desc;
        this.type = type;
    }
}
