package cn.allbs.utils.JBF293K.enums;

import cn.allbs.utils.JBF293K.format.data.AbstractParser;
import cn.allbs.utils.JBF293K.format.data.EleFireParser;
import cn.allbs.utils.JBF293K.format.data.FireDoorParser;
import lombok.Getter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 枚举 FireAndEleEnum
 * </p>
 *
 * @author ChenQi
 * @since 2023/2/21 16:37
 */
@Getter
public enum FireAndEleEnum {

    /**
     * 防火门
     */
    FIRE_DOOR("防火门", 0xfb) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new FireDoorParser(ins);
        }
    },

    /**
     * 电气火灾
     */
    ELE_FIRE("电气火灾", 0xfc) {
        @Override
        public AbstractParser execute(InputStream ins) {
            return new EleFireParser(ins);
        }
    };

    private final String orderName;

    private final Integer code;

    private final Integer control;

    private final Integer circuit;

    private final Integer part;

    public abstract AbstractParser execute(InputStream in);

    public static final Map<Integer, FireAndEleEnum> FIRE_AND_ELE_ENUM_MAP = new HashMap<>();

    static {
        FireAndEleEnum[] faes = FireAndEleEnum.values();
        for (FireAndEleEnum fae : faes) {
            FIRE_AND_ELE_ENUM_MAP.put(fae.code, fae);
        }
    }

    FireAndEleEnum(String orderName, Integer code) {
        this.orderName = orderName;
        this.code = code;
        this.control = null;
        this.circuit = null;
        this.part = null;
    }
}
