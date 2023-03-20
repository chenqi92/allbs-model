package cn.allbs.utils.gb26875.enums.system;

import com.google.common.base.Strings;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.*;

/**
 * 建筑消防设施操作信息
 *
 * @author ChenQi
 */
@Getter
@RequiredArgsConstructor
public enum Type24SSEnum {

    /**
     *
     */
    _0("无操作", "复位"),

    _1("无操作", "消音"),

    _2("无操作", "手动报警"),

    _3("无操作", "警情消除"),

    _4("无操作", "自检"),

    _5("无操作", "查岗应答"),

    _6("无操作", "测试"),

    _7("", "");

    private static final Table<Integer, String, String> DES_TABLE = HashBasedTable.create();

    static {
        Type24SSEnum[] enums = Type24SSEnum.values();
        for (Type24SSEnum e : enums) {
            e.binaryTransMap.forEach((k, v) -> {
                DES_TABLE.put(Integer.valueOf(e.name().substring(1)), k, v);
            });
        }
    }

    /**
     * 二进制0位代表的意思
     */
    private final Map<String, String> binaryTransMap = new HashMap<>();

    Type24SSEnum(String bTrans0, String bTrans1) {
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
                if (!Strings.isNullOrEmpty(trans) && !"无操作".equals(trans)) {
                    resList.add(trans);
                }
            }
        }
        Collections.reverse(resList);
        return resList;
    }

}
