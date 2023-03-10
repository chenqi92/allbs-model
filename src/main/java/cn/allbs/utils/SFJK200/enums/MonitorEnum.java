package cn.allbs.utils.SFJK200.enums;

import cn.hutool.core.convert.Convert;
import lombok.Getter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 枚举 MonitorEnum
 * </p>
 * 控制器状态定义
 *
 * @author ChenQi
 * @since 2023/3/8 13:28
 */
@Getter
public enum MonitorEnum {

    BIT_0("CANBUS 联网故障"),

    BIT_1("CRT 连接故障"),

    BIT_2("预留串口故障"),

    BIT_3("网络通讯故障"),

    BIT_4("1 回路开路故障"),

    BIT_5("1 回路短路故障"),

    BIT_6("1 回路主从通讯故障"),

    BIT_7("2 回路开路故障"),

    BIT_8("2 回路短路故障"),

    BIT_9("2 回路主从通讯故障"),

    BIT_10("3 回路开路故障"),

    BIT_11("3 回路短路故障"),

    BIT_12("3 回路主从通讯故障"),

    BIT_13("4 回路开路故障"),

    BIT_14("4 回路短路故障"),

    BIT_15("4 回路主从通讯故障");

    private final Integer bit;

    private final String desc;

    MonitorEnum(String desc) {
        this.bit = Integer.valueOf(this.name().replace("BIT_", ""));
        this.desc = desc;
    }

    public static final Map<Integer, String> MONITOR_ENUM_MAP = new HashMap<>(16);

    static {
        MonitorEnum[] monitorEnums = MonitorEnum.values();
        for (MonitorEnum monitor : monitorEnums) {
            MONITOR_ENUM_MAP.put(monitor.bit, monitor.desc);
        }
    }

    public static List<String> binaryTrans(byte[] bs) {
        List<String> resList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            sb.append(String.format("%08d", Convert.toLong(Integer.toBinaryString(b))));
        }
        char[] binaryChars = sb.reverse().toString().toCharArray();
        int len = binaryChars.length;
        for (int i = 0; i < len; i++) {
            if (binaryChars[i] == '1') {
                resList.add(MONITOR_ENUM_MAP.get(i));
            }
        }
        return resList;
    }
}
