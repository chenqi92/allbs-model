package cn.allbs.utils.SFJK200.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 枚举 DetectorEnum
 * </p>
 * 探测器定义
 *
 * @author ChenQi
 * @since 2023/3/8 13:25
 */
@Getter
public enum DetectorEnum {

    BIT_0("低限报警"),

    BIT_1("高限报警"),

    BIT_2("发生故障"),

    BIT_3("屏蔽或预留"),

    BIT_4("启动"),

    BIT_5("发生反馈"),

    BIT_6("预留"),

    BIT_7("预留");

    private final String desc;

    private final Integer bit;

    public static Map<Integer, String> DETECTOR_ENUM_MAP = new HashMap<>(8);

    static {
        DetectorEnum[] detectorEnums = DetectorEnum.values();
        for (DetectorEnum detectorEnum : detectorEnums) {
            DETECTOR_ENUM_MAP.put(detectorEnum.getBit(), detectorEnum.getDesc());
        }
    }

    DetectorEnum(String desc) {
        this.desc = desc;
        this.bit = Integer.valueOf(this.name().replace("BIT_", ""));
    }

    public static List<String> binaryTrans(byte bs) {
        List<String> resList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%08d", new BigDecimal(Integer.toBinaryString(bs)).longValue()));
        char[] binaryChars = sb.reverse().toString().toCharArray();
        int len = binaryChars.length;
        for (int i = 0; i < len; i++) {
            if (binaryChars[i] == '1') {
                resList.add(DETECTOR_ENUM_MAP.get(i));
            }
        }
        return resList;
    }
}
