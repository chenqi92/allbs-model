package cn.allbs.utils.JBF293K.enums;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 FireDoorsType
 * </p>
 * 防火门部件-类型
 *
 * @author ChenQi
 * @since 2023/2/21 16:14
 */
@Getter
public enum FireDoorsTypeEnum {

    /**
     * 单常开
     */
    SINGLE_NORMALLY_OPEN("单常开", (short) 0x1),

    /**
     * 单常闭
     */
    SINGLE_NORMALLY_CLOSED("单常闭", (short) 0x2),

    /**
     * 双常开
     */
    DUAL_NORMALLY_OPEN("双常开", (short) 0x3),

    /**
     * 双常闭
     */
    DUAL_NORMALLY_CLOSED("双常闭", (short) 0x4),
    ;

    public static final Map<Short, String> DOOR_TYPE = new HashMap<>(4);

    static {
        FireDoorsTypeEnum[] types = FireDoorsTypeEnum.values();
        for (FireDoorsTypeEnum t : types) {
            DOOR_TYPE.put(t.type, t.desc);
        }
    }

    private final String desc;

    private final short type;

    FireDoorsTypeEnum(String desc, Short type) {
        this.desc = desc;
        this.type = type;
    }

}
