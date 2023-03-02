package cn.allbs.utils.gb26875.enums.system;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 建筑消防设施部件模拟量值
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type3AQEnum {

    /**
     * 模拟量类型
     */
    _0(0, "未用", "", "", null),

    _1(1, "事件计数", "件", "0~32000", 1D),

    _2(2, "高度", "m", "0~320", 0.01),

    _3(3, "温度", "°C", "-273~+3200", 0.1),

    _4(4, "压力", "MPa", "0~3200", 0.1),

    _5(5, "压力", "kPa", "0~3200", 0.1),

    _6(6, "气体浓度", "%LEL", "0~100", 0.1),

    _7(7, "时间", "s", "0~32000", 1D),

    _8(8, "电压", "V", "0~3200", 0.1),

    _9(9, "电流", "A", "0~3200", 0.1),

    _10(10, "流量", "L/s", "0~3200", 0.1),

    _11(11, "风量", "m³/min", "0~3200", 0.1),

    _12(12, "风速", "m/s", "0~20", 1D);

    /**
     * 模拟量类型值
     */
    private final int type;

    private final String explain;

    private final String unit;

    private final String effectRange;

    private final Double min;

    public static final Map<Short, Type3AQEnum> AQ_ENUMS = new HashMap<>();

    static {
        Type3AQEnum[] items = Type3AQEnum.values();
        for (Type3AQEnum item : items) {
            AQ_ENUMS.put((short) item.getType(), item);
        }
    }
}
