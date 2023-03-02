package cn.allbs.utils.gb26875.enums.system;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 建筑消防设施系统状态 消息体系统类型定义
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type1STEnum {

    /**
     * 消息体定义
     */
    _0(0, "通用"),

    _1(1, "火灾报警系统"),

    _2(2, "预留"),

    _3(3, "预留"),

    _4(4, "预留"),

    _5(5, "预留"),

    _6(6, "预留"),

    _7(7, "预留"),

    _8(8, "预留"),

    _9(9, "预留"),

    _10(10, "消防联动控制器"),

    _11(11, "消火栓系统"),

    _12(12, "自动喷水灭火系统"),

    _13(13, "气体灭火系统"),

    _14(14, "水喷雾灭火系统(泵启动方式)"),

    _15(15, "水喷雾灭火系统(压力容器启动方式)"),

    _16(16, "泡沫灭火系统"),

    _17(17, "干粉灭火系统"),

    _18(18, "防烟排烟系统"),

    _19(19, "防火门及卷帘系统"),

    _20(20, "消防电梯"),

    _21(21, "消防应急广播"),

    _22(22, "消防应急照明和疏散指示系统"),

    _23(23, "消防电源"),

    _24(24, "消防电话");

    private final int type;

    private final String trans;

    public static final Map<Short, String> TYPE_1_MAP = new HashMap<>();

    static {
        Type1STEnum[] enums = Type1STEnum.values();
        for (Type1STEnum item : enums) {
            TYPE_1_MAP.put((short) item.getType(), item.getTrans());
        }
    }

}
