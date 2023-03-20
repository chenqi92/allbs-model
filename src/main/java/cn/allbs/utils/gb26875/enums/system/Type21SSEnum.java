package cn.allbs.utils.gb26875.enums.system;

import com.google.common.base.Strings;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用户信息传输装置运行状态
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type21SSEnum {

    /**
     *
     */
    _0("测试状态", "正常监视状态"),

    _1("无火警", "火警"),

    _2("无故障", "故障"),

    _3("主电正常", "主电故障"),

    _4("备电正常", "备电故障"),

    _5("通信信道正常", "与监控中心通信信道故障"),

    _6("监测连接线路正常", "监测连接线路故障"),

    _7("", "");

    private static final Table<Integer, String, String> DES_TABLE = HashBasedTable.create();

    static {
        Type21SSEnum[] enums = Type21SSEnum.values();
        for (Type21SSEnum e : enums) {
            e.binaryTransMap.forEach((k, v) -> {
                DES_TABLE.put(Integer.valueOf(e.name().substring(1)), k, v);
            });
        }
    }

    /**
     * 二进制0位代表的意思
     */
    private final Map<String, String> binaryTransMap = new HashMap<>();

    Type21SSEnum(String bTrans0, String bTrans1) {
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
