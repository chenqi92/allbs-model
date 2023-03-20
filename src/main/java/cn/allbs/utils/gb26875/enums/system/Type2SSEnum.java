package cn.allbs.utils.gb26875.enums.system;

import com.google.common.base.Strings;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

/**
 * 建筑消防设施部件状态含义
 * 低字节传输在前
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type2SSEnum {

    /**
     *
     */
    _0("测试状态", "正常运行状态"),

    _1("无火警", "火警"),

    _2("无故障", "故障"),

    _3("无屏蔽", "屏蔽"),

    _4("无监督", "监督"),

    _5("停止(关闭)", "启动(开启)"),

    _6("无反馈", "反馈"),

    _7("未延时", "延时状态"),

    _8("电源正常", "电源故障"),

    _9("", ""),

    _10("", ""),

    _11("", ""),

    _12("", ""),

    _13("", ""),

    /**
     * 预留
     */
    _14("", ""),

    /**
     * 预留
     */
    _15("", "");

    private static final Table<Integer, String, String> DES_TABLE = HashBasedTable.create();

    static {
        Type2SSEnum[] enums = Type2SSEnum.values();
        for (Type2SSEnum e : enums) {
            e.binaryTransMap.forEach((k, v) -> {
                DES_TABLE.put(Integer.valueOf(e.name().substring(1)), k, v);
            });
        }
    }

    /**
     * 二进制0位代表的意思
     */
    private final Map<String, String> binaryTransMap = new HashMap<>();

    Type2SSEnum(String bTrans0, String bTrans1) {
        this.binaryTransMap.put("0", bTrans0);
        this.binaryTransMap.put("1", bTrans1);
    }

    /**
     * @param bs
     * @return
     */
    public static List<String> binaryTrans(byte[] bs) {
        List<String> resList = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (byte b : bs) {
            sb.append(String.format("%08d", new BigDecimal(Integer.toBinaryString(b)).longValue()));
        }
        char[] binaryChars = sb.toString().toCharArray();
        int len = binaryChars.length;
        for (int i = 0; i < len; i++) {
            if (DES_TABLE.contains(len - 1 - i, String.valueOf(binaryChars[i]))) {
                String trans = DES_TABLE.get(len - 1 - i, String.valueOf(binaryChars[i]));
                if (!Strings.isNullOrEmpty(trans)) {
                    resList.add(trans);
                }
            }
        }
        Collections.reverse(resList);
        return resList;
    }

}
